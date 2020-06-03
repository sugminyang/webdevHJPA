package org.hyojeong.stdmgt;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class DateTest {

	@Test
	public void test() {
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println(nowDate);
	}

}
