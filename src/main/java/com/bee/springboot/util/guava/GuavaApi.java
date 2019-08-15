package com.bee.springboot.util.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaApi {

    public static void main(String[] args) throws ExecutionException {

        //第一个例子
        /*Cache<Integer, String> cache = CacheBuilder.newBuilder().build();
        cache.put(1, "a");
        System.out.println(cache.getIfPresent(1));
        System.out.println(cache.getIfPresent(2));*/



        //如果没有缓存，R让他自动加载一个缓存值
        //有两种方法可以实现"如果有缓存则返回；否则运算、缓存、然后返回"。
       /* LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(
                new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return "key-" + key;
                    }
                }
        );
        cache.put("test", "a");
        System.out.println(cache.getIfPresent("test"));
        try {
            System.out.println(cache.get("2"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        /*a
        key-2*/


        //
       /* Cache<Integer, String> cache = CacheBuilder.newBuilder().build();
        cache.put(1, "a");
        System.out.println(cache.getIfPresent(1));
        try {
            String value = cache.get(2, new Callable<String>() {
                public String call() throws Exception {
                    return "hello,world";
                }
            });
            System.out.println(value);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        /*a
                hello,world*/


        /*基于容量的回收:
        规定缓存项的数目不超过固定值，只需使用CacheBuilder.maximumSize(long)。缓存将尝试回收最近没有使用或总体上很少使用的缓存项。
        ——警告：在缓存项的数目达到限定值之前，即缓存项的数目逼近限定值时缓存就可能进行回收操作。这个size指的是cache中的条目数，不是内存大小或是其他.
                */

       /* 基于容量的缓存回收还可以指定缓存项的权重，使用CacheBuilder.weigher(Weigher)指定一个权重函数，并且用CacheBuilder.maximumWeight(long)指定最大总重。
        缓存回收也是在重量逼近限定值时就进行了，还要知道重量是在缓存创建时计算的，因此要考虑重量计算的复杂度。*/

      /*  Cache<Integer, String> cache = CacheBuilder.newBuilder().maximumSize(2).build();
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");
        System.out.println(cache.asMap());
        System.out.println(cache.getIfPresent(1));
        System.out.println(cache.getIfPresent(2));
        cache.put(4, "d");
        System.out.println(cache.asMap());*/


   /*     CacheBuilder.maximumSize(long)，CacheBuilder.maximumWeight(long)是互斥的，只能二选一。
        CacheBuilder.maximumSize(long)中如果每个条目占用内存空间都是相同的，就等价于限制了缓存空间的总大小；如果每个缓存条目大小不定，那么就没有办法限制总的内存大小。
        CacheBuilder.maximumWeight(long)可以用来控制内存。比如我们将总权重设置为1G(代表内存空间大小)，而每个缓存条目的权重都是缓存值实际占用的内存空间大小
*/
      /*  Cache<Integer, Integer> cache = CacheBuilder.newBuilder().maximumWeight(100)
                .weigher(new Weigher<Integer, Integer>() {
                    public int weigh(Integer key, Integer value) {
                        if (value % 2 == 0) {
                            return 20;
                        } else {
                            return 5;
                        }
                    }
                }).build();
//         放偶数
        for (int i = 0; i <= 20; i += 2) {
            cache.put(i, i);
        }
        System.out.println(cache.asMap());
        cache.invalidateAll();
        for (int i = 1; i < 10; i += 1) {
            cache.put(i, i);
        }
        System.out.println(cache.asMap());*/



       /* guava 提供两种定时回收的方法
        expireAfterAccess(long, TimeUnit):缓存项在给定时间内没有被读/写访问，则回收。请注意这种缓存的回收顺序和基于大小回收一样。
        expireAfterWrite(long, TimeUnit):缓存项在给定时间内没有被写访问（创建或覆盖），则回收。如果认为缓存数据总是在固定时候后变得陈旧不可用，这种回收方式是可取的。*/

      /*  Cache<Integer, Integer> cache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).build();
        cache.put(1,1);
        System.out.println(cache.asMap());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cache.asMap());*/


     /*   基于时间的缓存回收可以和基于容量的缓存回收一起使用，这样可以避免：当缓存创建速度，远远大于过期速度的时候出现OOM的问题。
        Cache<Integer, Integer> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(3, TimeU*/

       /* Cache<Integer, Integer> cache = CacheBuilder.newBuilder().expireAfterAccess(3, TimeUnit.SECONDS).build();
        cache.put(1,1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cache.getIfPresent(1);
        System.out.println(cache.asMap());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cache.asMap());*/


        /*手动清除缓存
        任何时候，你都可以显式地清除缓存项，而不是等到它被回收：

        个别清除：Cache.invalidate(key)

        批量清除：Cache.invalidateAll(keys)

        清除所有缓存项：Cache.invalidateAll()*/
       /* Cache<Integer, Integer> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(3, TimeUnit.SECONDS).build();
        cache.put(1, 1);
        cache.put(2, 2);
        cache.invalidateAll(Lists.newArrayList(1));
        System.out.println(cache.asMap());
        cache.put(3, 3);
        System.out.println(cache.asMap());
        cache.invalidateAll();
        System.out.println(cache.asMap());*/


       // 监听删除删除
       /* LoadingCache<Integer, Integer> cache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).removalListener(new RemovalListener<Object, Object>() {

            public void onRemoval(RemovalNotification<Object, Object> notification) {
                System.out.println("remove key[" + notification.getKey() + "],value[" + notification.getValue() + "],remove reason[" + notification.getCause() + "]");
            }
        }).recordStats().build(
                new CacheLoader<Integer, Integer>() {
                    @Override
                    public Integer load(Integer key) throws Exception {
                        return 2;
                    }
                }
        );
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.asMap());
        cache.invalidateAll();
        System.out.println(cache.asMap());
        cache.put(3, 3);
        try {
            System.out.println(cache.getUnchecked(3));
            Thread.sleep(4000);
            System.out.println(cache.getUnchecked(3));//如果没有就返回2
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

      /*  刷新表示为键加载新值，这个过程可以是异步的。在刷新操作进行时，缓存仍然可以向其他线程返回旧值.

                而不像回收操作，读缓存的线程必须等待新值加载完成。

        如果刷新过程抛出异常，缓存将保留旧值，*/
        /*LoadingCache<Integer, Integer> cache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).removalListener(new RemovalListener<Object, Object>() {
            public void onRemoval(RemovalNotification<Object, Object> notification) {
                System.out.println("remove key[" + notification.getKey() + "],value[" + notification.getValue() + "],remove reason[" + notification.getCause() + "]");
            }
        }).recordStats().build(
                new CacheLoader<Integer, Integer>() {
                    @Override
                    public Integer load(Integer key) throws Exception {
                        return 99;
                    }
                }
        );
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.asMap());
        cache.refresh(1);
        System.out.println(cache.asMap());*/

        //Cache<String, List<Book>> cache = CacheBuilder.newBuilder().expireAfterAccess(3, TimeUnit.SECONDS).build();

        LoadingCache<String, List<Book>> cache = CacheBuilder.newBuilder().expireAfterAccess(3, TimeUnit.SECONDS).build(
                new CacheLoader<String, List<Book>>() {
                    @Override
                    public List<Book> load(String key) throws Exception {
                        List<Book> books = new ArrayList<Book>();
                        for (int i = 0; i < 3; i++) {
                            Book b = new Book(i);
                            books.add(b);
                        }
                        return books;
                    }
                }
        );

        /*List<Book> books = cache.get("points",
                new Callable<List<Book>>() {
                    public List<Book> call() {
                        BookDao dao = new BookDao();
                        List<Book> list = (List<Book>) dao.executeSQL();
                        if (null == list || list.size() <= 0) {
                            list = new ArrayList<Book>();
                        }
                        return list;
                    }
                });*/
        List<Book> books = cache.get("points");
        for (Book book : books) {
            System.out.println(book);
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // cache.put("test", "a");
        List<Book> points = cache.getIfPresent("points");
        for (Book book : points) {
            System.out.println(book);
        }
        System.out.println(cache.asMap());
       /* try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cache.asMap());*/
    }
}
