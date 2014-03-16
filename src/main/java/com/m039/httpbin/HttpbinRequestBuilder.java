/** HttpbinRequestBuilder.java ---
 */

package com.m039.httpbin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Testing an HTTP Library can become difficult sometimes. <a
 * href="http://postbin.org">Postbin</a> is fantastic for testing POST
 * requests, but not much else. This exists to cover all kinds of HTTP
 * scenarios. Additional endpoints are being considered
 * (e.g. /deflate).
 *
 * <br><br>
 *
 * All endpoint responses are JSON-encoded.
 *
 * <br><br>
 *
 * Created: 03/16/14
 *
 * <br><br>
 * 
 * @author Dmitry Mozgin
 * @see <a href="http://httpbin.org">http://httpbin.org</a>
 */
public class HttpbinRequestBuilder {

    static private String BASE_URL = "http://httpbin.org";

    /**
     * / This page.
     */
    static public String baseUrl() {
        return BASE_URL;
    }

    /**
     * /ip Returns Origin IP.
     */
    static public String ip() {
        return BASE_URL + "/ip";
    }

    /**
     * /user-agent Returns user-agent.
     */
    static public String userAgent() {
        return BASE_URL + "/user-agent";
    }

    /**
     * /headers Returns header dict.
     */
    static public String headers() {
        return BASE_URL + "/headers";
    }

    /**
     * /get Returns GET data.
     */
    static public String get() {
        return BASE_URL + "/get";
    }

    /**
     * /post Returns POST data.
     */
    static public String post() {
        return BASE_URL + "/post";
    }

    /**
     * /put Returns PUT data.
     */
    static public String put() {
        return BASE_URL + "/put";
    }

    /**
     * /patch Returns PATCH data.
     */
    static public String patch() {
        return BASE_URL + "/patch";
    }

    /**
     * /delete Returns DELETE data
     */
    static public String delete() {
        return BASE_URL + "/delete";
    }

    /**
     * /gzip Returns gzip-encoded data.
     */
    static public String gzip() {
        return BASE_URL + "/gzip";
    }

    /**
     * /html Renders an HTML Page.
     */
    static public String html() {
        return BASE_URL + "/html";
    }

    /**
     * /robots.txt Returns some robots.txt rules.
     */
    static public String robots() {
        return BASE_URL + "/robots.txt";
    }

    /**
     * /deny Denied by robots.txt file.
     */
    static public String deny() {
        return BASE_URL + "/deny";
    }

    /**
     * /cache 200 unless If-Modified-Since was sent, then 304.
     */
    static public String cache() {
        return BASE_URL + "/cache";
    }

    /**
     * /status/:code Returns given HTTP Status code.
     *
     * @param httpStatusCode
     */
    static public String status(String httpStatusCode) {
        if (httpStatusCode == null) {
            throw new IllegalArgumentException("httpStatusCode is null");
        }

        return BASE_URL + "/status/" + httpStatusCode;
    }

    /**
     * /response-headers?key=val Returns given response headers.
     *
     * @param keyValuePairs
     */
    static public String responseHeaders(Map<String, String> keyValuePairs) {
        return BASE_URL + "/response-headers" + toQueryString(keyValuePairs);
    }

    /**
     * /redirect/:n 302 Redirects n times.
     *
     * @param times times to redirect
     */
    static public String redirect(int times) {
        return BASE_URL + "/redirect/" + times;
    }

    /**
     * /redirect-to?url=foo 302 Redirects to the foo URL.
     *
     * @param url url to redirect to
     */
    static public String redirectTo(String url) {
        return BASE_URL + "/redirect-to?url=" + url;
    }

    /**
     * /relative-redirect/:n 302 Relative redirects n times.
     *
     * @param times times to redierect
     */
    static public String relativeRedirect(int times) {
        return BASE_URL + "/relative-redirect/" + times;
    }

    /**
     * /cookies Returns cookie data.
     */
    static public String cookies() {
        return BASE_URL + "/cookies";
    }

    /**
     * /cookies/set?name=value Sets one or more simple cookies.
     *
     * @param keyValuePairs
     */
    static public String cookiesSet(Map<String, String> keyValuePairs) {
        return BASE_URL + "/cookies/set" + toQueryString(keyValuePairs);
    }

    /**
     * /cookies/delete?name Deletes one or more simple cookies.
     *
     * @param listOfNames
     */
    static public String cookiesDelete(String ... listOfNames) {
        return BASE_URL + "/cookies/delete" + toQueryString(listOfNames);
    }

    /**
     * /basic-auth/:user/:passwd HTTPBasic Auth.
     *
     * @param user
     * @param password
     */
    static public String basicAuth(String user, String password) {
        return BASE_URL + "/basic-auth/" + user + "/" + password;
    }

    /**
     * /hidden-basic-auth/:user/:passwd 404'd BasicAuth.
     *
     * @param user
     * @param password
     */
    static public String hiddenBasicAuth(String user, String password) {
        return BASE_URL + "/hidden-basic-auth/" + user + "/" + password;
    }

    /**
     * /digest-auth/:qop/:user/:passwd HTTP Digest Auth.
     *
     * @param qop
     * @param user
     * @param password
     */
    static public String digestAuth(String qop, String user, String password) {
        return BASE_URL + "/digest-auth/" + qop + "/" + user + "/" + password;
    }

    /**
     * /stream/:n Streams n–100 lines.
     *
     * @param lines
     */
    static public String stream(int lines) {
        return BASE_URL + "/stream/" + lines;
    }

    /**
     * /delay/:n Delays responding for n–10 seconds.
     *
     * @param delayInSeconds
     */
    static public String delay(int delayInSeconds) {
        return BASE_URL + "/delay/" + delayInSeconds;
    }

    /**
     * /drip?numbytes=400&duration=3&delay=1 Drip drip drip.
     *
     * @param numbytes
     * @param duration
     * @param delay
     */
    static public String drip(int numbytes, int duration, int delay) {
        String pairs[] = new String[] {
            "numbytes=" + numbytes,
            "duration=" + duration,
            "delay=" + delay
        };

        return BASE_URL + "/drip" + toQueryString(pairs);
    }

    static private String toQueryString(String listOfNames[]) {
        if (listOfNames == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (String name : listOfNames) {
            sb.append(name + "&");
        }

        return toQueryString(sb);
    }

    static private String toQueryString(Map<String, String> args) {
        if (args == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        try {
            for (Map.Entry<String, String> pair : args.entrySet()) {
                String key = pair.getKey();
                String value = pair.getValue();

                sb.append(String.format("%s=%s&", key, URLEncoder.encode(value, "UTF-8")));
            }
        } catch (UnsupportedEncodingException e) {
            // ignore
        }

        return toQueryString(sb);
    }

    static private String toQueryString(StringBuilder sb) {
        int length = sb.length();

        if (length > 0) {
            sb.delete(length - 1, length);
            return "?" + sb.toString();
        } else {
            return sb.toString();
        }
    }

} // HttpbinRequestBuilder
