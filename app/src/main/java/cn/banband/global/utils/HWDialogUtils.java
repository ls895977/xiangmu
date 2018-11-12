package cn.banband.global.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import cn.banband.global.activity.HWBaseMainActivity;
import cn.banband.global.dialog.HWConfirmDialog;
import cn.banband.global.dialog.HWLoadingDialog;
import cn.banband.global.dialog.HWLoadingSmallDialog;

public class HWDialogUtils {
    private static HWLoadingDialog mLoadingDialog;
    private static HWLoadingSmallDialog mLoadingSmallDialog;

    public static void showToast(Context context, String text){
        try {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoadingToast(Context context){
        showLoadingToast(context,null);
    }

    public static void showLoadingToast(Context context, String title){
        mLoadingDialog = new HWLoadingDialog(context, title);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.show();
    }
    public static void hideLoadingToast(){
        if(mLoadingDialog !=null){
            mLoadingDialog.dismiss();
        }
    }


    public static void showLoadingSmallToast(Context context){
        mLoadingSmallDialog = new HWLoadingSmallDialog(context);
        mLoadingSmallDialog.setCanceledOnTouchOutside(false);
        mLoadingSmallDialog.show();
    }

    public static void hideLoadingSmallToast(){
        if(mLoadingSmallDialog!=null) {
            mLoadingSmallDialog.dismiss();
        }
    }

    public static void showConfirmDialog(Context context,String message , String title , final HWConfirmDialog.ConfirmDialogListener confirmDialogListener , final int actionType){
        HWConfirmDialog.Builder builder = new HWConfirmDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if(confirmDialogListener!=null)confirmDialogListener.OnConfirmOkClick(actionType);
            }
        });
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}
