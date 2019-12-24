package com.wkq.order.utils;

import android.content.Context;
import android.text.TextUtils;

import com.wkq.base.utlis.SharedPreferencesHelper;
import com.wkq.net.ApiBuild;
import com.wkq.order.modlue.login.MoveDbMoveType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class MoveDbMoveTypeUtlis {


    public static void putType(Context context) {

        String data = SharedPreferencesHelper.getInstance(context).getValue(Constant.MOVE_DB_TYPE_KEY);

//        if (!TextUtils.isEmpty(data)) return;


        List<MoveDbMoveType> types = new ArrayList<>();
        types.add(new MoveDbMoveType(37, "西部"));
        types.add(new MoveDbMoveType(10768, "战争"));
        types.add(new MoveDbMoveType(10767, "脱口秀"));
        types.add(new MoveDbMoveType(10766, "肥皂剧"));
        types.add(new MoveDbMoveType(10765, "科幻"));
        types.add(new MoveDbMoveType(10764, "真人秀"));
        types.add(new MoveDbMoveType(10763, "新闻"));
        types.add(new MoveDbMoveType(10762, "儿童"));
        types.add(new MoveDbMoveType(9648, "悬疑"));
        types.add(new MoveDbMoveType(10751, "家庭"));
        types.add(new MoveDbMoveType(18, "文艺"));
        types.add(new MoveDbMoveType(99, "纪录片"));
        types.add(new MoveDbMoveType(80, "犯罪"));
        types.add(new MoveDbMoveType(35, "喜剧"));
        types.add(new MoveDbMoveType(10759, "动作冒险"));
        types.add(new MoveDbMoveType(16, "动画片"));
        types.add(new MoveDbMoveType(28, "动作"));
        String typeStrings = MoveDbMoveTypeUtlis.list2json(types);
        SharedPreferencesHelper.getInstance(context).setValue(Constant.MOVE_DB_TYPE_KEY, typeStrings);
    }

    //将集合转化为json

    public static String list2json(List<MoveDbMoveType> list) {
        if (list == null) return "";
        JSONArray array = new JSONArray();
        JSONObject jsonObject = null;
        MoveDbMoveType info = null;
        for (int i = 0; i < list.size(); i++) {
            info = list.get(i);
            jsonObject = new JSONObject();
            try {
                jsonObject.put("id", info.getId() + "");
                jsonObject.put("type", info.getType());
                array.put(jsonObject);

            } catch (JSONException e) {
                return "";
            }

        }
        return array.toString();

    }

    /**
     * 将string 转化为 list
     *
     * @param listJsons
     * @return
     */
    public static List<MoveDbMoveType> json2list(String listJsons) {

        List<MoveDbMoveType> items = new ArrayList<>();
        if (TextUtils.isEmpty(listJsons)) return items;

        JSONArray array = null;
        try {
            array = new JSONArray(listJsons);
            JSONObject object = null;
            MoveDbMoveType item = null;
            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);
                String key = object.getString("id");
                String value = object.getString("type");
                int id = Integer.parseInt(key);
                item = new MoveDbMoveType(id, value);
                items.add(item);
            }
            return items;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;

    }


    public static String getType(Context conetxt, int id) {
        String data = SharedPreferencesHelper.getInstance(conetxt).getValue(Constant.MOVE_DB_TYPE_KEY);

        List<MoveDbMoveType> list = json2list(data);
        if (list != null && list.size() > 0) {

            for (MoveDbMoveType moveDbMoveType : list) {
                if (id == moveDbMoveType.getId()) {
                    return moveDbMoveType.getType();
                }
            }

        }
        return "其他";
    }
}
