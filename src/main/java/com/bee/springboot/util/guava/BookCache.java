package com.bee.springboot.util.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookCache {

	public static Cache<String, List<Book>> cache = CacheBuilder.newBuilder().expireAfterAccess(8, TimeUnit.SECONDS).maximumSize(2)
			.build();

}
