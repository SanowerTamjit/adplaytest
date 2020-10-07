package com.santam.adplaytest.cotroller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.santam.adplaytest.entity.mongodb.TotalstatsMongoEntity;
import com.santam.adplaytest.entity.mysql.TotalstatsMysqlEntity;
import com.santam.adplaytest.service.TotalstatsService;
import com.santam.adplaytest.util.Util;
import com.santam.adplaytest.util.httpconnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/totalstats")
public class TotalstatsController {


    @Autowired
    TotalstatsService totalstatsService;

    @GetMapping(value = "/addToMongo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addToMongo() {

        JsonObject returnObj = new JsonObject();
        String covidBDTotalStatsResp = new httpconnector().getRequest("https://corona-bd.herokuapp.com/stats");
        if (!covidBDTotalStatsResp.isEmpty()) {
            try {
                JsonObject covidBDTotalStatsJson = (JsonObject) new JsonParser().parse(covidBDTotalStatsResp);
                TotalstatsMongoEntity totalstatsEntity = new TotalstatsMongoEntity();
                totalstatsEntity.setdLast24(covidBDTotalStatsJson.get("death").getAsJsonObject().get("last24").getAsInt());
                totalstatsEntity.setdTotal(covidBDTotalStatsJson.get("death").getAsJsonObject().get("total").getAsInt());
                totalstatsEntity.setpLast24(covidBDTotalStatsJson.get("positive").getAsJsonObject().get("last24").getAsInt());
                totalstatsEntity.setpTotal(covidBDTotalStatsJson.get("positive").getAsJsonObject().get("total").getAsInt());
                totalstatsEntity.setrLast24(covidBDTotalStatsJson.get("recovered").getAsJsonObject().get("last24").getAsInt());
                totalstatsEntity.setrTotal(covidBDTotalStatsJson.get("recovered").getAsJsonObject().get("total").getAsInt());
                totalstatsEntity.settLast24(covidBDTotalStatsJson.get("test").getAsJsonObject().get("last24").getAsInt());
                totalstatsEntity.settTotal(covidBDTotalStatsJson.get("test").getAsJsonObject().get("total").getAsInt());
                totalstatsEntity.setUpdatedAt(new Util().covertString2Timestamp(covidBDTotalStatsJson.get("updated_on").getAsString()));
                totalstatsService.addTotalStatsMongo(totalstatsEntity);

                returnObj.addProperty("status", Boolean.TRUE);
                returnObj.addProperty("msg", "Data Updated Successfully (Ops: Covid BD TotalStats ---> MongoDB)");

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
    public List<TotalstatsMongoEntity> getDataMongo() {
        return totalstatsService.getTotalStatsMongo();

    }

    @GetMapping(value = "/deleteDataMongo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteDataMongo() {

        JsonObject returnObj = new JsonObject();
        totalstatsService.deleteAllTotalStatsMongo();

        returnObj.addProperty("status", Boolean.TRUE);
        returnObj.addProperty("msg", "Truncated all TotalStats data in mongoDB");


        return new ResponseEntity<>(returnObj.toString(), HttpStatus.OK);
    }


    //Mysql
    @GetMapping(value = "/addToMysql", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveToMysql() {

        JsonObject returnObj = new JsonObject();
        int success = 0, failed = 0, exists = 0;
        List<TotalstatsMongoEntity> getTotalStatusMongoList = totalstatsService.getTotalStatsMongo();

        for (TotalstatsMongoEntity totalstatsMongoEntity : getTotalStatusMongoList) {
            
            try{
                TotalstatsMysqlEntity totalstatsMysqlEntity = new TotalstatsMysqlEntity();
                totalstatsMysqlEntity.setdLast24(totalstatsMongoEntity.getdLast24());
                totalstatsMysqlEntity.setdTotal(totalstatsMongoEntity.getdTotal());
                totalstatsMysqlEntity.setpLast24(totalstatsMongoEntity.getpLast24());
                totalstatsMysqlEntity.setpTotal(totalstatsMongoEntity.getpTotal());
                totalstatsMysqlEntity.setrLast24(totalstatsMongoEntity.getrLast24());
                totalstatsMysqlEntity.setrTotal(totalstatsMongoEntity.getrTotal());
                totalstatsMysqlEntity.settLast24(totalstatsMongoEntity.gettLast24());
                totalstatsMysqlEntity.settTotal(totalstatsMongoEntity.gettTotal());
                Timestamp updatedAt = new java.sql.Timestamp(totalstatsMongoEntity.getUpdatedAt().getTime());
                totalstatsMysqlEntity.setUpdatedAt(updatedAt);
                if(!totalstatsService.isExists(updatedAt)){
                    totalstatsService.addTotalStatsMysql(totalstatsMysqlEntity);
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
        totalstatsService.deleteAllTotalStatsMysql();

        returnObj.addProperty("status", Boolean.TRUE);
        returnObj.addProperty("msg", "Truncated all data in mysql");


        return new ResponseEntity<>(returnObj.toString(), HttpStatus.OK);
    }


}
