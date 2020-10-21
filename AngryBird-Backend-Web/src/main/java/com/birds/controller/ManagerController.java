package com.birds.controller;

import com.alibaba.fastjson.JSONObject;
import com.birds.pojo.Manager;
import com.birds.pojo.Result;
import com.birds.service.ManagerAndRoleService;
import com.birds.service.ManagerService;
import com.birds.utils.PageRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private ManagerAndRoleService managerAndRoleService;
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
        fund_ranking_data.add("pageSize","1000");
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

    @GetMapping("/funds")
    public Result getRankingFund(@RequestParam(defaultValue = "1") String pagenum,@RequestParam(defaultValue = "10") String pagesize){
        HttpHeaders headers = new HttpHeaders();
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
        fund_ranking_data.add("pageIndex",pagenum);
        fund_ranking_data.add("SortColumn","GSZZL");
        fund_ranking_data.add("pageSize",pagesize);
        fund_ranking_data.add("MobileKey","61aa039c6a7877193c3f0fa901e31e24%7C%7Ciemi_tluafed_me");
        fund_ranking_data.add("plat","Android");
        fund_ranking_data.add("ISABNORMAL","true");
        HttpEntity<MultiValueMap<String, String>> fundRankingDataRequestEntity = new HttpEntity<>(fund_ranking_data, headers);
        ResponseEntity<String> response = restTemplate.exchange("https://fundmobapi.eastmoney.com/FundMNewApi/FundMNValuationList", HttpMethod.POST, fundRankingDataRequestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错
        String rankingData = response.getBody();
        if(rankingData != null){
            JSONObject jsonObject = (JSONObject) JSON.parse(rankingData);
            return Result.success(jsonObject);
        }
        return Result.error(null);
    }

    @GetMapping("/get_funds")
    public Result getFundsByIds(@RequestParam String ids){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> set_value_data= new LinkedMultiValueMap<>();
        set_value_data.add("cccccccTokenErrorStop","true");
        set_value_data.add("plat","Android");
        set_value_data.add("appType","ttjj");
        set_value_data.add("deviceid","61aa039c6a7877193c3f0fa901e31e24||iemi_tluafed_me");
        set_value_data.add("product","EFund");
        set_value_data.add("Version","6.3.2");
        set_value_data.add("Fcodes",ids);
        HttpEntity<MultiValueMap<String, String>> setValueRequestEntity = new HttpEntity<>(set_value_data, headers);
        ResponseEntity<String> response = restTemplate.exchange("https://fundmobapi.eastmoney.com/FundMNewApi/FundMNFInfo", HttpMethod.POST, setValueRequestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错

        String body = response.getBody();
        if(body != null){
            JSONObject jsonObject = (JSONObject) JSON.parse(body);
            return Result.success(jsonObject);
        }
        return Result.error(null);
    }

    @GetMapping("/get_top")
    public Result getBigTop(){
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> index_data= new LinkedMultiValueMap<>();
        //添加请求的参数
        index_data.add("noooooLog", "true");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(index_data, headers);
        ResponseEntity<String> response = restTemplate.exchange("https://push2.eastmoney.com/api/qt/ulist.np/get?fltt=2&secids=1.000001&fields=f2,f3,f14&ut=5c5bcf5bc6b33c18078e1dc9f70253d5", HttpMethod.POST, requestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错
        String body = response.getBody();
        if(body != null){
            JSONObject jsonObject = (JSONObject) JSON.parse(body);
            return Result.success(jsonObject);
        }
        return Result.error(null);
    }


    @GetMapping("/querySimpleWeather")
    public Result querySimpleWeather(@RequestParam String city){
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> data= new LinkedMultiValueMap<>();
        data.add("city",city);
        data.add("key","152d9a4681ce7a2e15757577d8aaf380");
        HttpEntity<MultiValueMap<String, String>> fundRankingDataRequestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://apis.juhe.cn/simpleWeather/query", HttpMethod.POST, fundRankingDataRequestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错
        String rankingData = response.getBody();
        if(rankingData != null){
            JSONObject jsonObject = (JSONObject) JSON.parse(rankingData);
            return Result.success(jsonObject);
        }
        return Result.error(null);
    }

    @GetMapping("/getTouTiaoNews")
    public Result getTouTiaoNews(@RequestParam String type){
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> data= new LinkedMultiValueMap<>();
        data.add("type",type);
        data.add("key","9beddf9130edfdf193662b55787fc561");
        HttpEntity<MultiValueMap<String, String>> fundRankingDataRequestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://v.juhe.cn/toutiao/index", HttpMethod.POST, fundRankingDataRequestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错
        String rankingData = response.getBody();
        if(rankingData != null){
            JSONObject jsonObject = (JSONObject) JSON.parse(rankingData);
            return Result.success(jsonObject);
        }
        return Result.error(null);
    }

    @GetMapping("/getBrand")
    public Result getBrand(@RequestParam String firstLetter){
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> data= new LinkedMultiValueMap<>();
        data.add("first_letter",firstLetter);
        data.add("key","294ed37379d3ee3987b90c474d25ed6e");
        HttpEntity<MultiValueMap<String, String>> fundRankingDataRequestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://apis.juhe.cn/cxdq/brand", HttpMethod.POST, fundRankingDataRequestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错
        String rankingData = response.getBody();
        if(rankingData != null){
            JSONObject jsonObject = (JSONObject) JSON.parse(rankingData);
            return Result.success(jsonObject);
        }
        return Result.error(null);
    }

    @GetMapping(value ="/today",produces = {"application/json;charset=UTF-8"})
    public Result today(@RequestParam String month, @RequestParam String day) throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> data= new LinkedMultiValueMap<>();
        data.add("v","1.0");
        data.add("month",month);
        data.add("day",day);
        data.add("key","5083597720029cff924b6e1bd2991502");
        HttpEntity<MultiValueMap<String, String>> fundRankingDataRequestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://api.juheapi.com/japi/toh", HttpMethod.POST, fundRankingDataRequestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错
        String rankingData =response.getBody();

        if(rankingData != null){
            JSONObject jsonObject = (JSONObject) JSON.parse(rankingData);
            System.out.println(jsonObject);
            return Result.success(jsonObject);
        }
        return Result.error(null);
    }

    @GetMapping("/youjia")
    public Result getTodayYouJia() {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> data= new LinkedMultiValueMap<>();
        data.add("key","5e40b66f0f7c06c83a4b772585025c67");
        HttpEntity<MultiValueMap<String, String>> fundRankingDataRequestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://apis.juhe.cn/gnyj/query", HttpMethod.POST, fundRankingDataRequestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错
        String rankingData =response.getBody();
        if(rankingData != null){
            JSONObject jsonObject = (JSONObject) JSON.parse(rankingData);
            System.out.println(jsonObject);
            return Result.success(jsonObject);
        }
        return Result.error(null);
    }


    @GetMapping("/getRole/{id}")
    public Result getRole(@PathVariable("id") Integer id){
        return managerAndRoleService.findManagerRoleByManagerId(id);
    }


    @GetMapping("/getPermission/{id}")
    public Result getPermission(@PathVariable("id") Integer id){
        return managerAndRoleService.findPermissionByManagerId(id);
    }


}
