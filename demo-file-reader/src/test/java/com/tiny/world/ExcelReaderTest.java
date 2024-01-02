package com.tiny.world;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.tiny.world.domain.VersionedTrade;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExcelReaderTest {

	@Test
	void testReadExcelFile() {
		String fileName = TestFileUtil.getResourcesPath("input/versionedTrade.xlsx");
		assertThat(readExcel(fileName)).hasSize(2);
	}

	public static List<VersionedTrade> readExcel(String filepath) {
		List<VersionedTrade> versionedTrades = new ArrayList<>();
		EasyExcel.read(filepath, VersionedTrade.class, new PageReadListener<VersionedTrade>(dataList -> {
			for (VersionedTrade demoData : dataList) {
				versionedTrades.add(demoData);
			}
		})).sheet().doRead();
		return versionedTrades;
	}

}
