package team.yqby.platform.common;

import java.net.*;
import java.util.List;

public class CookiesUtil {
    private static CookieManager manager = new CookieManager();

    public static void storeCoo(URI uri, String strCoo) {

        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(manager);
        HttpCookie cookie = new HttpCookie("Cookie: ", strCoo);
        cookie.setMaxAge(Integer.MAX_VALUE);
        manager.getCookieStore().add(uri, cookie);
    }

    public static HttpCookie getCookies() {
        HttpCookie res = null;
        CookieStore store = manager.getCookieStore();

        List<URI> uris = store.getURIs();
        for (URI ur : uris) {
            List<HttpCookie> cookies = store.get(ur);
            for (HttpCookie coo : cookies) {
                res = coo;
            }
        }
        return res;
    }
}
