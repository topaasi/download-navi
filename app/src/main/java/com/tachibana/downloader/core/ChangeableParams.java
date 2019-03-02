package com.tachibana.downloader.core;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/*
 * Bundle of parameters that need to be changed in the download.
 * All parameters nullable.
 */

public class ChangeableParams implements Parcelable
{
    public String url;
    public String fileName;
    public String description;
    public Uri dirPath;
    public Boolean wifiOnly;
    public Boolean retry;

    public ChangeableParams() {}

    public ChangeableParams(@NonNull Parcel source)
    {
        url = source.readString();
        fileName = source.readString();
        description = source.readString();
        dirPath = source.readParcelable(Uri.class.getClassLoader());
        byte wifiOnlyVal = source.readByte();
        if (wifiOnlyVal != -1)
            wifiOnly = wifiOnlyVal > 0;
        byte retryVal = source.readByte();
        if (retryVal != -1)
            retry = retryVal > 0;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(url);
        dest.writeString(fileName);
        dest.writeString(description);
        dest.writeParcelable(dirPath, flags);
        if (wifiOnly == null)
            dest.writeByte((byte)-1);
        else
            dest.writeByte((byte)(wifiOnly ? 1 : 0));
        if (retry == null)
            dest.writeByte((byte)-1);
        else
            dest.writeByte((byte)(retry ? 1 : 0));
    }

    public static final Parcelable.Creator<ChangeableParams> CREATOR =
            new Parcelable.Creator<ChangeableParams>()
            {
                @Override
                public ChangeableParams createFromParcel(Parcel source)
                {
                    return new ChangeableParams(source);
                }

                @Override
                public ChangeableParams[] newArray(int size)
                {
                    return new ChangeableParams[size];
                }
            };

    @Override
    public String toString()
    {
        return "ChangeableParams{" +
                "url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                ", description='" + description + '\'' +
                ", dirPath=" + dirPath +
                ", wifiOnly=" + wifiOnly +
                ", retry=" + retry +
                '}';
    }
}
