/*
package com.bee.springboot.controller;


import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RestController  //注解相当于@ResponseBody ＋ @Controller合在一起的作用
@RequestMapping("/es")
public class ESController {

    @Autowired
    private TransportClient transportClient;

    @GetMapping("/get/book/novel")
    public ResponseEntity get(@RequestParam(name="id",defaultValue = "") String id) {
        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
       */
/*GetResponse result = this.client.prepareGet("book", "novel", id);
        if(!result.isExits()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }*//*

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("add/book/novel")
    public ResponseEntity get(@RequestParam(name="title",defaultValue = "") String title,
                              @RequestParam(name="author",defaultValue = "") String author,
                              @RequestParam(name="word_count",defaultValue = "") int word_count,
                              @RequestParam(name="publish_date",defaultValue = "")
                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate
                              ) {
        try {
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", title)
                    .field("author", author)
                    .field("word_count", word_count)
                    .field("publish_date", publishDate.getTime())
                    .endObject();
            IndexResponse result = this.transportClient.prepareIndex("book", "novel")
                    .setSource(content)
                    .get();
            return new ResponseEntity(result.getId(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    */
/**
     * 根据id删除
     * @param id
     * @return
     *//*

    @GetMapping("delete/book/novel")
    public ResponseEntity delete(@RequestParam(name="id") String id) {
        DeleteResponse result = this.transportClient.prepareDelete("book", "novel", id).get();
        return new ResponseEntity(result.getResult().toString(),HttpStatus.OK);
    }


    @PostMapping("update/book/novel")
    public ResponseEntity update(@RequestParam(name="title",defaultValue = "") String title,
                              @RequestParam(name="author",defaultValue = "") String author,
                              @RequestParam(name="id",defaultValue = "") String id

    ) {
        UpdateRequest update = new UpdateRequest("book", "novel", id);
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject();
                    if(title!=null){
                        builder.field("title", title);
                    }

                    if(author!=null){
                        builder.field("author", author);
                    }
                    builder.endObject();
                    update.doc(builder);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            UpdateResponse result = this.transportClient.update(update).get();
            return new ResponseEntity(result.getResult().toString(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("query/book/novel")
    public ResponseEntity query(@RequestParam(name="title",defaultValue = "") String title,
                                @RequestParam(name="author",defaultValue = "") String author,
                                @RequestParam(name="gt_word_count",defaultValue = "") String gt_word_count,
                                @RequestParam(name="lt_word_count",defaultValue = "") String lt_word_count){

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if(author!=null){
            boolQuery.must(QueryBuilders.matchQuery("author",author));
        }

        if(title!=null){
            boolQuery.must(QueryBuilders.matchQuery("author",title));
        }
        SearchRequestBuilder builder = this.transportClient.prepareSearch("book")
                .setTypes("novel")
                .setQuery(boolQuery)
                .setFrom(0)
                .setSize(10);
        System.out.println(builder);

        SearchResponse response = builder.get();
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        SearchHits hits = response.getHits();
        for(SearchHit hit:hits){
            result.add(hit.getSource());
        }
        return new ResponseEntity(result,HttpStatus.OK);
    }


}
*/
