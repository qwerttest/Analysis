package com.wj.base.analysis.json;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 捕捉Json解析异常,避免出现由此引起 的crash
 *
 * create by wangjian on 2018-03-14
 */
public class JsonUtils {
    private static Gson gson;

    static {
        gson = new Gson();
    }

    public static Gson gson() {
        if(null == gson){
            gson = new Gson();
        }
        return gson;
    }

    /* -------------------------------转化--------------------------------------------*/
    /**
     * 对象转成json字符串
     *
     * @param <T>
     */
    public static <T> String obj2JsonStr(T t, String def) {
        try {
            return gson().toJson(t);
        } catch (Exception e) {
            e.printStackTrace();
            //   TODO
        }
        return def;
    }

    /**
     * json转成对象
     *
     * @param <T>
     */
    public static <T> T json2Obj(String json, Class<T> cls) {
        try {
            return gson().fromJson(json, cls);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            //   TODO       VLDebug.logE("json解析异常" + json, e.getMessage());
        }
        return null;
    }

    /**
     * json字符串直接转成List,可以解析组合List{@link #}
     * @param cls  Bean[].class
     * @param json String json串
     * @param <T>
     * @return
     */

	public static final <T> List<T> json2List(final String json, final Class<T[]> cls)
	{
        try {
            T[] array = gson().fromJson(json, cls);
            return  Arrays.asList(array);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Json解析为map，Json key只能为
     * */
    public static <T> Map<String, T> json2Map(String json){
        try {
            Map<String, T> map = null;
            if (gson != null) {
                map = gson().fromJson(json, new TypeToken<Map<String, T>>(){}.getType());
            }
            return map;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     * Json解析为map，Json key只能为
     * */
    public static <T> List<Map<String, T>> json2MapList(String json) {
        try {
            return gson().fromJson(json, new TypeToken<List<Map<String, T>>>(){}.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static JSONObject getJSONObject(String json) {
        return getJSONObject(json, new JSONObject());
    }

    public static JSONObject getJSONObject(String json, JSONObject def) {
        JSONObject jsonObject = def;
        try {
            jsonObject = new JSONObject(json);
        } catch (Exception e) {  //不只是JsonException，还可能是别的异常
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONArray getJSONArray(String json) {
        return getJSONArray(json, new JSONArray());
    }

    public static JSONArray getJSONArray(String json, JSONArray def) {
        JSONArray jsonArray = def;
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /*--------------------------------------------------读值---------------------------------*/

    public static double getDouble(String json, String key) {
        return getDouble(getJSONObject(json), key, 0.0);
    }

    public static double getDouble(JSONObject json, String key) {
        return getDouble(json, key, 0.0);
    }

    public static double getDouble(JSONObject json, String key, double def) {
        double value = def;
        if (canParse(json, key)) {
            try {
                value = json.getDouble(key);
            } catch (JSONException e) {
                //   TODO  VLDebug.logE("getInt=" + e.getMessage());
            }
        }
        return value;
    }

    public static int getInt(JSONObject json, String key) {
        return getInt(json, key, -1);
    }

    public static int getInt(String json, String key) {
        return getInt(getJSONObject(json), key, -1);
    }

    public static int getInt(JSONObject json, String key, int def) {
        int value = def;
        if (canParse(json, key)) {
            try {
                value = json.getInt(key);
            } catch (JSONException e) {
                //   TODO VLDebug.logE("getInt=" + e.getMessage());
            }
        }
        return value;
    }

    public static long getLong(JSONObject json, String key) {
        return getLong(json, key, -1);
    }

    public static long getLong(String json, String key) {
        return getLong(getJSONObject(json), key, -1);
    }

    public static long getLong(JSONObject json, String key, long def) {
        long value = def;
        if (canParse(json, key)) {
            try {
                value = json.getLong(key);
            } catch (JSONException e) {
                //   TODO     VLDebug.logE("getInt=" + e.getMessage());
            }
        }
        return value;
    }

    public static String getString(JSONObject json, String key) {
        return getString(json, key, "");
    }

    public static String getString(String json, String key) {
        return getString(getJSONObject(json), key, "");
    }


    public static String getString(JSONObject json, String key, String def) {
        String value = def;
        if (canParse(json, key)) {
            try {
                value = json.getString(key);
            } catch (JSONException e) {
                //   TODO   VLDebug.logE("getString=" + e.getMessage());
                //   TODO VLDebug.logE("json解析异常" + json, e.getMessage());
            }
        }
        return value;
    }

    public static JSONObject getJSONObject(JSONObject json, String key) {
        return getJSONObject(json, key, new JSONObject());
    }

    public static JSONObject getJSONObject(JSONObject json, String key, JSONObject def) {
        JSONObject returnObj = def;
        if (canParse(json, key)) {
            try {
                returnObj = json.getJSONObject(key);
            } catch (JSONException e) {
                //   TODO    VLDebug.logE("getJSONObject=" + e.getMessage());
            }
        }
        return returnObj;
    }

    public static JSONArray getJSONArray(JSONObject json, String key) {
        return getJSONArray(json, key, new JSONArray());
    }

    public static JSONArray getJSONArray(String json, String key) {
        return getJSONArray(getJSONObject(json), key, new JSONArray());
    }

    public static JSONArray getJSONArray(JSONObject json, String key, JSONArray def) {
        JSONArray array = def;
        if (canParse(json, key)) {
            try {
                array = json.getJSONArray(key);
            } catch (JSONException e) {
                //   TODO  VLDebug.logE("getJSONArray=" + e.getMessage());
            }
        }
        return array;
    }

    public static JSONObject getJSONObject(JSONArray array, int index) {
        return getJSONObject(array, index, new JSONObject());
    }

    public static JSONObject getJSONObject(JSONArray array, int index, JSONObject def) {
        JSONObject returnObj = def;
        if (array != null && array.length() > 0) {
            try {
                returnObj = array.getJSONObject(index);
            } catch (JSONException e) {
                //   TODO   VLDebug.logE("getJSONObject=" + e.getMessage());
            }
        }
        return returnObj;
    }

    /* -----------------------------------------设置值------------------------------------*/

    public static JSONObject putValue(JSONObject json, String key, Object value) {
        try {
            json.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 判断数据是否可以解析，可以则return true;
     */
    private static boolean canParse(JSONObject json, String key) {
        if (json != null || TextUtils.isEmpty(key)) {
            return json.has(key);
        }
        return false;
    }
}
