/** HttpbinRequestBuilderTest.java --- 
 *
 * Copyright (C) 2014 Dmitry Mozgin
 *
 * Author: Dmitry Mozgin <m0391n@gmail.com>
 *
 * 
 */

package com.m039.httpbin;

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
	public void testA() {
        Assert.assertEquals(HttpbinRequestBuilder.A, "ad");
	}
    
} // HttpbinRequestBuilderTest
