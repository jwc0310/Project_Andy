package example.andy.com.emandy.utils;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import example.andy.com.emandy.base.BaseFragment;
import example.andy.com.emandy.constants.CommonVars;

/**
 * Created by Andy on 16/6/7.
 */
public class UiUtils {

    public static void changeFragment(FragmentActivity activity, int resid, BaseFragment fragment){
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();

        String current_page = fragment.getClass().getName();

        if(CommonVars.CURRENT_HOME_PAGE.equals(current_page)){
            //return;
        }
        ft.replace(resid, fragment);
        ft.commit();
        CommonVars.CURRENT_HOME_PAGE = current_page;
    }

}
