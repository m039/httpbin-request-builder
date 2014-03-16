/** HttpbinRequestBuilderTest.java ---
 *
 * Copyright (C) 2014 Dmitry Mozgin
 *
 * Author: Dmitry Mozgin <m0391n@gmail.com>
 *
 *
 */

package com.m039.httpbin;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

/**
 *
 *
 * Created: 03/16/14
 *
 * @author Dmitry Mozgin
 * @version
 * @since
 */
public class HttpbinRequestBuilderTest {

    @Test
    public void testConstRequest() {
        //
        // All of this tests are silly
        //

        assertEquals(HttpbinRequestBuilder.baseUrl(), "http://httpbin.org");
        assertEquals(HttpbinRequestBuilder.ip(), "http://httpbin.org/ip");
        assertEquals(HttpbinRequestBuilder.userAgent(), "http://httpbin.org/user-agent");
        assertEquals(HttpbinRequestBuilder.headers(), "http://httpbin.org/headers");
        assertEquals(HttpbinRequestBuilder.get(), "http://httpbin.org/get");
        assertEquals(HttpbinRequestBuilder.post(), "http://httpbin.org/post");
        assertEquals(HttpbinRequestBuilder.put(), "http://httpbin.org/put");
        assertEquals(HttpbinRequestBuilder.patch(), "http://httpbin.org/patch");
        assertEquals(HttpbinRequestBuilder.delete(), "http://httpbin.org/delete");
        assertEquals(HttpbinRequestBuilder.gzip(), "http://httpbin.org/gzip");
        assertEquals(HttpbinRequestBuilder.html(), "http://httpbin.org/html");
        assertEquals(HttpbinRequestBuilder.robots(), "http://httpbin.org/robots.txt");
        assertEquals(HttpbinRequestBuilder.deny(), "http://httpbin.org/deny");
        assertEquals(HttpbinRequestBuilder.cache(), "http://httpbin.org/cache");
        assertEquals(HttpbinRequestBuilder.cookies(), "http://httpbin.org/cookies");
    }

    @Test
    public void testVariableRequest() {
        String baseUrl = "http://httpbin.org";
        Map<String, String> map = new HashMap<String, String>();

        // status
        assertEquals(HttpbinRequestBuilder.status("300"), baseUrl + "/status/300");

        // response-headers
        map.clear();
        assertEquals(HttpbinRequestBuilder.responseHeaders(map),
                     baseUrl + "/response-headers");
        map.put("k1", "v1");
        assertEquals(HttpbinRequestBuilder.responseHeaders(map),
                     baseUrl + "/response-headers?k1=v1");
        map.put("k2", "v2");
        assertEquals(HttpbinRequestBuilder.responseHeaders(map),
                     baseUrl + "/response-headers?k1=v1&k2=v2");

        // redirect
        assertEquals(HttpbinRequestBuilder.redirect(3), 
                     baseUrl + "/redirect/3");

        // redirect-to
        assertEquals(HttpbinRequestBuilder.redirectTo("foo"),
                     baseUrl + "/redirect-to?url=foo");

        // relative-redirect
        assertEquals(HttpbinRequestBuilder.relativeRedirect(4),
                     baseUrl + "/relative-redirect/4");

        // cookies/set
        assertEquals(HttpbinRequestBuilder.cookiesSet(map),
                     baseUrl + "/cookies/set?k1=v1&k2=v2");

        // cookies/delete
        assertEquals(HttpbinRequestBuilder.cookiesDelete(), 
                     baseUrl + "/cookies/delete");
        assertEquals(HttpbinRequestBuilder.cookiesDelete("c1"), 
                     baseUrl + "/cookies/delete?c1");
        assertEquals(HttpbinRequestBuilder.cookiesDelete("c1", "c2"), 
                     baseUrl + "/cookies/delete?c1&c2");

        // basic-auth
        assertEquals(HttpbinRequestBuilder.basicAuth("u", "p"), 
                     baseUrl + "/basic-auth/u/p");
        
        // hidden-basic-auth
        assertEquals(HttpbinRequestBuilder.hiddenBasicAuth("u", "p"), 
                     baseUrl + "/hidden-basic-auth/u/p");

        // digest-auth
        assertEquals(HttpbinRequestBuilder.digestAuth("q", "u", "p"), 
                     baseUrl + "/digest-auth/q/u/p");

        // stream
        assertEquals(HttpbinRequestBuilder.stream(5),
                     baseUrl + "/stream/5");

        // delay
        assertEquals(HttpbinRequestBuilder.delay(6),
                     baseUrl + "/delay/6");

        // drip
        assertEquals(HttpbinRequestBuilder.drip(7, 8, 9),
                     baseUrl + "/drip?numbytes=7&duration=8&delay=9");
    }

    private void assertEquals(Object a, Object b) {
        Assert.assertEquals(a, b);
    }

} // HttpbinRequestBuilderTest
