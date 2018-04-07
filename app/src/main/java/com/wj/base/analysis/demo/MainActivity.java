package com.wj.base.analysis.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wj.base.analysis.json.JsonUtils;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 解析list
     * */
    public void json2List(View v){
        String str = "[{\"a\":\"1\",\"c\":\"2\"},{\"a\":\"1\",\"c\":\"2\"},{\"a\":\"1\",\"c\":\"2\"},{\"a\":\"1\",\"c\":\"2\"},{\"a\":\"1\",\"c\":\"2\"},{\"a\":\"1\",\"c\":\"2\"},{\"a\":\"1\",\"c\":\"2\"},{\"a\":\"1\",\"c\":\"2\"}]";
        List<A> list = JsonUtils.json2List(str, A[].class);
        for (A a : list){
            System.out.println(a.toString());
        }
    }

    /**
     * 组合list
     * */
    public void json2List2(View v){
        String str = "[{\"a\":\"1\",\"c\":\"2\", \"bs\":[{\"b\":\"3\",\"d\":\"4\"},{\"b\":\"3\",\"d\":\"4\"}]},{\"a\":\"1\",\"c\":\"2\", \"bs\":[{\"b\":\"3\",\"d\":\"4\"},{\"b\":\"3\",\"d\":\"4\"}]}]";
        List<A> list = JsonUtils.json2List(str, A[].class);
        for (A a : list){
            System.out.println(a.getBs().get(0).getB());
        }
    }

    public void json2List3(View v){
        String str = "[\"1\",\"2\",\"3\"]";
        List<String> list = JsonUtils.json2List(str, String[].class);
        for (String a : list){
            System.out.println(a);
        }
    }

    public void json2List4(View v){
        String str = "[1,2,3]";
        List<Integer> list = JsonUtils.json2List(str, Integer[].class);
        for (int a : list){
            System.out.println(a);
        }
    }

    /**
     * 解析对象，里面包含list
     * */
    public void json2Obj(View v){
        String str = "{\"a\":\"1\",\"c\":\"2\", \"bs\":[{\"b\":\"3\",\"d\":\"4\"}]}";
        A a = JsonUtils.json2Obj(str, A.class);
        System.out.println(a.getBs().get(0).getB());
    }

    /**
     * 解析为Map
     * */
    public void json2Map(View v){
        String str = "{\"a\":\"1\",\"b\":\"c\"}";
        Map<String, Object> map = JsonUtils.json2Map(str);
        System.out.println(map.toString());
    }

    /**
     * 解析为List<Map<String,Object>>
     * */
    public void json2MapList(View v){
        String str = "[{\"a\":\"1\",\"b\":\"c\"},{\"a\":\"1\",\"b\":\"c\"},{\"a\":\"1\",\"b\":\"c\"}]";
        List<Map<String, Object>> map = JsonUtils.json2MapList(str);
        System.out.println(map.toString());
    }

    public static class A{
        private String a;
        private String c;
        private List<B> bs;

        @Override
        public String toString() {
            String str = "a=" + a + "   " + "c=" + c;
            if(null != bs){
                str += (" B = " + bs.toString());
            }
            return str;
        }

        public String getA() {
            return a;
        }

        public String getC() {
            return c;
        }

        public List<B> getBs() {
            return bs;
        }
    }

    public static class B{
        private String d;
        private String b;

        @Override
        public String toString() {
            return "d=" + d + "   " + "c=" + b ;
        }

        public String getD() {
            return d;
        }

        public String getB() {
            return b;
        }
    }
}
