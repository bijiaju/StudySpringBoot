package com.bee.springboot.util.guava;

import java.util.ArrayList;
import java.util.List;


public class Main {
	public static void main(String[] args) {
		/*try {
			System.out.println("第一次调用dao方法,正确状态:应该调用Dao里的方法");
			List<Book> books = BookCache.cache.get("points",
					new Callable<List<Book>>() {
						public List<Book> call() {
							BookDao dao = new BookDao();
							List<Book> list = (List<Book>) dao.executeSQL();
							if (null == list || list.size() <= 0) {
								list = new ArrayList<Book>();
							}
							return list;
						}
					});
			for (Book book : books) {
				System.out.println(book);
			}
			System.out.println("第二次调用dao方法,正确状态:不调用Dao里的方法");
			List<Book> books2 = BookCache.cache.get("points",
					new Callable<List<Book>>() {

						public List<Book> call() {
							BookDao dao = new BookDao();
							List<Book> list = (List<Book>) dao.executeSQL();
							if (null == list || list.size() <= 0) {
								list = new ArrayList<Book>();
							}
							return list;
						}
					});
			for (Book book : books2) {
				System.out.println(book);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			Thread.currentThread();
			Thread.sleep(TimeUnit.SECONDS.toMillis(10));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("休息十秒后,第三次调用dao方法,正确状态:调用Dao里的方法");
			List<Book> books = BookCache.cache.get("points",
					new Callable<List<Book>>() {

						public List<Book> call() {
							BookDao dao = new BookDao();
							List<Book> list = (List<Book>) dao.executeSQL();
							if (null == list || list.size() <= 0) {
								list = new ArrayList<Book>();
							}
							return list;
						}
					});
			for (Book book : books) {
				System.out.println(book);
			}
			System.out.println("第四次调用dao方法,正确状态:不调用Dao里的方法");
			List<Book> books2 = BookCache.cache.get("points",
					new Callable<List<Book>>() {

						public List<Book> call() {
							BookDao dao = new BookDao();
							List<Book> list = (List<Book>) dao.executeSQL();
							if (null == list || list.size() <= 0) {
								list = new ArrayList<Book>();
							}
							return list;
						}
					});
			for (Book book : books2) {
				System.out.println(book);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
*/

		List<String> strs = new ArrayList<String>();
		strs.add("fuck");
		strs.add("中特");
		String msg = "fuck1中特";
		for(String str :strs){
			if(msg.contains(str)){
				System.out.println(str);

			}
		}
		/*if(strs.contains(msg)){
			System.out.println(msg);
		}else{
			System.out.println("xxxx");
		}*/
	}

	
}
