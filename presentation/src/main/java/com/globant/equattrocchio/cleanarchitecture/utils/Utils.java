package com.globant.equattrocchio.cleanarchitecture.utils;

import android.content.Context;
import android.text.Spanned;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.data.response.Image;

import static android.text.Html.fromHtml;

public class Utils {

    public static Spanned formatFullInformationOf(Image image, Context context) {
        Spanned spanned = null;

        if(image != null && context != null){
            spanned = fromHtml("<b>" + context.getString(R.string.title_details) + "</b>" +
                    "<br><br>Site: " + image.getSite() + "<br><br>" +
            "Copyright: " + image.getCopyright() + "<br><br>" +
            "Id: " + image.getId() + "<br><br>" +
            "Source Id: " + image.getSourceId() + "<br><br>" +
            "Url: " + image.getUrl());
        }

        return spanned;
    }
}
