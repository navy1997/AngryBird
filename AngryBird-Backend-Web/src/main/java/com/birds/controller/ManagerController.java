package com.birds.controller;

import com.alibaba.fastjson.JSONObject;
import com.birds.pojo.Manager;
import com.birds.pojo.Result;
import com.birds.service.ManagerService;
import com.birds.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public Object login(@RequestBody Manager manager){
        Result result = managerService.login(manager.getUsername(), manager.getPassword());
        return result;
    }

    @GetMapping("/")
    public Object findManagers(@RequestParam(required = false) String query,@RequestParam(defaultValue = "1") Integer pagenum,@RequestParam(defaultValue = "2") Integer pagesize){
        PageRequest pageRequest = new PageRequest();
        if(query != null){
            query = "%" + query + "%";
            pageRequest.getQuery().put("username",query);
        }
        pageRequest.setPageNum(pagenum);
        pageRequest.setPageSize(pagesize);
        Result result = managerService.findManagers(pageRequest);
        return result;
    }

    @PostMapping("/")
    public Object addManager(@RequestBody Manager manager){
        return managerService.addManager(manager);
    }

    @PutMapping("/{uId}/state/{type}")
    public Object updateManagerState(@PathVariable("uId") Integer id,@PathVariable("type") Integer type){
        Manager manager = new Manager();
        manager.setIsDelete(type);
        manager.setId(id);
        return managerService.updateManager(manager);
    }

    @GetMapping("/{id}")
    public Object getManagerById(@PathVariable("id") Integer id){
        return managerService.findManagerById(id);
    }

    @PutMapping("/{id}")
    public Object updateManager(@PathVariable("id") Integer id,@RequestBody Manager manager){
        manager.setId(id);
        return managerService.updateManager(manager);
    }

    @DeleteMapping("/{id}")
    public Object deleteManagerById(@PathVariable("id") Integer id){
        return managerService.deleteManagerById(id);
    }

    @GetMapping("/jijin")
    public Result testRest(){
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> index_data= new LinkedMultiValueMap<>();
        //添加请求的参数
        index_data.add("noooooLog", "true");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(index_data, headers);
        ResponseEntity<String> response = restTemplate.exchange("https://push2.eastmoney.com/api/qt/ulist.np/get?fltt=2&secids=1.000001&fields=f2,f3,f14&ut=5c5bcf5bc6b33c18078e1dc9f70253d5", HttpMethod.POST, requestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错

        MultiValueMap<String, String> set_value_data= new LinkedMultiValueMap<>();
        set_value_data.add("cccccccTokenErrorStop","true");
        set_value_data.add("plat","Android");
        set_value_data.add("appType","ttjj");
        set_value_data.add("deviceid","61aa039c6a7877193c3f0fa901e31e24||iemi_tluafed_me");
        set_value_data.add("product","EFund");
        set_value_data.add("Version","6.3.2");
        set_value_data.add("Fcodes","320007,161726,003231,003096,005963,008888,008087");
        HttpEntity<MultiValueMap<String, String>> setValueRequestEntity = new HttpEntity<>(set_value_data, headers);
        ResponseEntity<String> response2 = restTemplate.exchange("https://fundmobapi.eastmoney.com/FundMNewApi/FundMNFInfo", HttpMethod.POST, setValueRequestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错

        MultiValueMap<String, String> fund_ranking_data= new LinkedMultiValueMap<>();
        fund_ranking_data.add("appType","ttjj");
        fund_ranking_data.add("Sort","desc");
        fund_ranking_data.add("product","EFund");
        fund_ranking_data.add("gToken","ceaf-215bf81ca205dbfb60840cd6867e4559");
        fund_ranking_data.add("version","6.3.2");
        fund_ranking_data.add("onFundCache","3");
        fund_ranking_data.add("deviceid","61aa039c6a7877193c3f0fa901e31e24%7C%7Ciemi_tluafed_me");
        fund_ranking_data.add("FundType","0");
        fund_ranking_data.add("BUY","true");
        fund_ranking_data.add("pageIndex","1");
        fund_ranking_data.add("SortColumn","GSZZL");
        fund_ranking_data.add("pageSize","30");
        fund_ranking_data.add("MobileKey","61aa039c6a7877193c3f0fa901e31e24%7C%7Ciemi_tluafed_me");
        fund_ranking_data.add("plat","Android");
        fund_ranking_data.add("ISABNORMAL","true");
        HttpEntity<MultiValueMap<String, String>> fundRankingDataRequestEntity = new HttpEntity<>(fund_ranking_data, headers);
        ResponseEntity<String> response3 = restTemplate.exchange("https://fundmobapi.eastmoney.com/FundMNewApi/FundMNValuationList", HttpMethod.POST, fundRankingDataRequestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错



        String indexData = response.getBody();
        String setValueData = response2.getBody();
        String rankingData = response3.getBody();



        if(indexData != null){
            System.out.println((JSONObject) JSON.parse(indexData));
            System.out.println("-----------------------------");
            System.out.println((JSONObject) JSON.parse(setValueData));
            System.out.println("-----------------------------");
            System.out.println((JSONObject) JSON.parse(rankingData));


            JSONObject data = (JSONObject) JSON.parse(indexData);
            JSONObject data2 = (JSONObject) JSON.parse(setValueData);
            System.out.println(data.get("rt"));

            List datas = (List)data2.get("Datas");
            for (Object m:datas) {
                System.out.println(((Map<String,String>)m).get("NAV"));
            }

            //data就是返回的结果
        }else{

        }
        return Result.success(null);
    }
}
