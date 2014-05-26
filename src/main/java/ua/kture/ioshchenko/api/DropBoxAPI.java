package ua.kture.ioshchenko.api;


import com.dropbox.core.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

@Component
public class DropBoxAPI {
    private static final String APP_KEY = "0j3sfwnrq6hmd51";
    private static final String APP_SECRET = "qcrxm8c08k68uk9";

    private String authUrl;
    private DbxRequestConfig config;
    private DbxWebAuthNoRedirect webAuth;

    public DropBoxAPI() {
        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
        config = new DbxRequestConfig(
                "fake_kture", Locale.getDefault().toString());
        webAuth = new DbxWebAuthNoRedirect(config, appInfo);
        authUrl = webAuth.start();
    }

    public String getAccessToken(String code) throws DbxException {
        DbxAuthFinish authFinish = webAuth.finish(code);
        return authFinish.accessToken;
    }

    public void uploadFile(String urlImage, String accessToken) throws IOException, DbxException {
        DbxClient client = new DbxClient(config, accessToken);

        String fileName = urlImage.substring(urlImage.lastIndexOf("/"), urlImage.length());
        URL url = new URL(urlImage);
        URLConnection uc = url.openConnection();
        long contentLenght = uc.getContentLength();

        BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
        try {
            DbxEntry.File uploadedFile = client.uploadFile(fileName,
                    DbxWriteMode.add(), contentLenght, inputStream);
        } finally {
            inputStream.close();
        }


    }

    public String getAuthUrl() {
        return authUrl;
    }
}
