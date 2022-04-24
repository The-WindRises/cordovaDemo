package com.booway.compass;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import com.amap.api.maps.MapsInitializer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

/**
 * This class echoes a string called from JavaScript.
 */
public class Compass extends CordovaPlugin
{

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException
    {
        if ("show".equals(action))
        {
            String message = args.getString(0);
            this.show(message, callbackContext);
            return true;
        }
        return false;
    }

    private void show(String message, CallbackContext callbackContext)
    {
        if (message != null && message.length() > 0)
        {
            privacyCompliance();
        } else
        {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void privacyCompliance()
    {
        MapsInitializer.updatePrivacyShow(cordova.getActivity(), true, true);
        SpannableStringBuilder spannable = new SpannableStringBuilder(
                "\"亲，感谢您对XXX一直以来的信任！我们依据最新的监管要求更新了XXX《隐私权政策》，特向您说明如下\n1.为向您提供交易相关基本功能，我们会收集、使用必要的信息；\n2.基于您的明示授权，我们可能会获取您的位置（为您提供附近的商品、店铺及优惠资讯等）等信息，您有权拒绝或取消授权；\n3.我们会采取业界先进的安全措施保护您的信息安全；\n4.未经您同意，我们不会从第三方处获取、共享或向提供您的信息；\n");
        spannable.setSpan(new ForegroundColorSpan(Color.BLUE), 35, 42, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        new AlertDialog.Builder(cordova.getActivity()).setTitle("温馨提示(隐私合规示例)").setMessage(spannable)
                .setPositiveButton("同意", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        MapsInitializer.updatePrivacyAgree(cordova.getActivity(), true);
                        Intent intent = new Intent(cordova.getContext(), CompassActivity.class);
                        cordova.getActivity().startActivity(intent);
                    }
                }).setNegativeButton("不同意", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        MapsInitializer.updatePrivacyAgree(cordova.getActivity(), false);
                    }
                }).show();
    }
}
