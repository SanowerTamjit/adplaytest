package com.santam.adplaytest.cotroller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.santam.adplaytest.entity.mongodb.DistrictstatsMongoEntity;
import com.santam.adplaytest.entity.mongodb.TotalstatsMongoEntity;
import com.santam.adplaytest.entity.mysql.DistrictstatsMysqlEntity;
import com.santam.adplaytest.entity.mysql.TotalstatsMysqlEntity;
import com.santam.adplaytest.service.DistrictstatsService;
import com.santam.adplaytest.service.TotalstatsService;
import com.santam.adplaytest.util.Util;
import com.santam.adplaytest.util.httpconnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/districtstats")
public class DistrictstatsController {


    @Autowired
    DistrictstatsService districtstatsService;

    //Mongo Ops
    @GetMapping(value = "/addToMongo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addToMongo() {

        JsonObject returnObj = new JsonObject();
        String covidBDDistrictStatsResp = new httpconnector().getRequest("https://corona-bd.herokuapp.com/district");
        if (!covidBDDistrictStatsResp.isEmpty()) {
            try {
                JsonObject covidBDDistrictStatsJson = (JsonObject) new JsonParser().parse(covidBDDistrictStatsResp);
                Timestamp lastUpdatedOn = new Util().covertString2Timestamp(covidBDDistrictStatsJson.get("updated_on").getAsString());

                List<DistrictstatsMongoEntity> allDistData = new ArrayList<>();
                covidBDDistrictStatsJson.get("data").getAsJsonArray().forEach(jsonElement -> {
                    JsonObject distObj = (JsonObject) jsonElement;
                    DistrictstatsMongoEntity districtstatsMongoEntity = new DistrictstatsMongoEntity();
                    districtstatsMongoEntity.setDistrict(distObj.get("name").getAsString());
                    districtstatsMongoEntity.setDistrictId(distObj.get("id").getAsInt());
                    districtstatsMongoEntity.setpCount(distObj.get("count").getAsInt());
                    districtstatsMongoEntity.settCount(distObj.get("prev_count").getAsInt());
                    districtstatsMongoEntity.setUpdatedAt(lastUpdatedOn);
                    allDistData.add(districtstatsMongoEntity);

                });
                districtstatsService.addDistrictStatsMongo(allDistData);

                returnObj.addProperty("status", Boolean.TRUE);
                returnObj.addProperty("msg", "Data Updated Successfully (Ops: Covid BD DistrictStats ---> MongoDB)");

            } catch (Exception e) {

                e.printStackTrace();
                returnObj.addProperty("status", Boolean.FALSE);
                returnObj.addProperty("msg", "Failed to retrive data. Please try again later.");
            }


        } else {
            returnObj.addProperty("status", Boolean.FALSE);
            returnObj.addProperty("msg", "Failed to load data. Please try again later.");
        }


        return new ResponseEntity<>(returnObj.toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/getDataMongo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DistrictstatsMongoEntity> getDataMongo() {

        return districtstatsService.getDistrictStatsMongo();
    }

    @GetMapping(value = "/deleteDataMongo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteDataMongo() {

        JsonObject returnObj = new JsonObject();
        districtstatsService.deleteAllDistrictStatsMongo();

        returnObj.addProperty("status", Boolean.TRUE);
        returnObj.addProperty("msg", "Truncated all DistrictStats data in mongoDB");


        return new ResponseEntity<>(returnObj.toString(), HttpStatus.OK);
    }


    //MySql Ops
    @GetMapping(value = "/addToMysql", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveToMysql() {

        JsonObject returnObj = new JsonObject();
        int success = 0, failed = 0, exists = 0;
        List<DistrictstatsMongoEntity> getDistrictStatusMongoList = districtstatsService.getDistrictStatsMongo();

        for (DistrictstatsMongoEntity districtstatsMongoEntity : getDistrictStatusMongoList) {

            try{
                DistrictstatsMysqlEntity districtstatsMysqlEntity = new DistrictstatsMysqlEntity();
                districtstatsMysqlEntity.setDistrict(districtstatsMongoEntity.getDistrict());
                districtstatsMysqlEntity.setDistrictId(districtstatsMongoEntity.getDistrictId());
                districtstatsMysqlEntity.setpCount(districtstatsMongoEntity.getpCount());
                districtstatsMysqlEntity.settCount(districtstatsMongoEntity.gettCount());
                Timestamp updatedAt = new Timestamp(districtstatsMongoEntity.getUpdatedAt().getTime());
                districtstatsMysqlEntity.setUpdatedAt(updatedAt);
                if(!districtstatsService.isExists(updatedAt, districtstatsMysqlEntity.getDistrictId())){
                    districtstatsService.addDistrictStatsMysql(districtstatsMysqlEntity);
                    success++;
                }else{
                    exists++;
                }
            }catch (Exception e){
                failed++;
            }

        }

        if(success > 0 || exists > 0)
            returnObj.addProperty("status", Boolean.TRUE);
        else
            returnObj.addProperty("status", Boolean.FALSE);


        returnObj.addProperty("successmsg", (success > 0) ? "Successfully inserted "+success +" record. " : (exists > 0) ? exists +" record(s) already have. " : "");
        returnObj.addProperty("failedmsg", (success == 0 && failed == 0 && exists == 0) ? "No data in MongoDB" : (failed > 0) ? failed +" record(s) couldn't insert. " : "");


        return new ResponseEntity<>(returnObj.toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/deleteDataMysql", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteDataMysql() {

        JsonObject returnObj = new JsonObject();
        districtstatsService.deleteAllDistrictStatsMysql();

        returnObj.addProperty("status", Boolean.TRUE);
        returnObj.addProperty("msg", "Truncated all data in mysql");


        return new ResponseEntity<>(returnObj.toString(), HttpStatus.OK);
    }



    @PostMapping(value = "/getDataMysql", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DistrictstatsMysqlEntity> getDataMysql() {

        return districtstatsService.getDistrictStatsMysql();
    }

}
