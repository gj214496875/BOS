package com.itheima.web.action;

import com.itheima.bean.Region;
import com.itheima.service.IRegionService;
import com.itheima.utils.PinYin4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 区域管理
 *
 * @author 郭军
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
    @Autowired
    private IRegionService regionService;
    private File regionFile;

    public String importXls() throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
        HSSFSheet sheet1 = workbook.getSheet("sheet1");
        List<Region> regions = new ArrayList<Region>();
        for (Row cells : sheet1) {
            if (cells.getRowNum() == 0) {
                continue;
            }
            Region region = new Region();
            region.setId(cells.getCell(0).getStringCellValue());
            String province = cells.getCell(1).getStringCellValue();
            region.setProvince(province);
            String city = cells.getCell(2).getStringCellValue();
            region.setCity(city);
            String district = cells.getCell(3).getStringCellValue();
            region.setDistrict(district);
            region.setPostcode(cells.getCell(4).getStringCellValue());
            //简码
            String s = province.substring(0, province.length() - 1) + city.substring(0, city.length() - 1) +
                    district.substring(0, district.length() - 1);
            String[] headByString = PinYin4jUtils.getHeadByString(s);
            String shortcode = StringUtils.join(headByString);
            region.setShortcode(shortcode);
            //城市编码
            String citycode = PinYin4jUtils.hanziToPinyin(city.substring(0, city.length() - 1), "");
            region.setCitycode(citycode);
            regions.add(region);
        }
        regionService.saveBatch(regions);
        return NONE;
    }

    public String pageList() throws IOException {
        regionService.pageList(pageBean);
        this.toJson(pageBean, "subareas");
        return NONE;
    }

    private String q;

    public String regionAjax() {
        List<Region> regionList = null;
        if (StringUtils.isNotBlank(q)) {
            regionList = regionService.findByQ(q);
        } else {
            regionList = regionService.findAll();
        }
        for (Region region : regionList) {
            region.getName();
        }
        this.toJson(regionList, "province", "city", "district", "postcode", "shortcode", "citycode", "subareas");
        return NONE;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }
}
