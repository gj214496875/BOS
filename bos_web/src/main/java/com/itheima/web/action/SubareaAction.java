package com.itheima.web.action;

import com.itheima.bean.Region;
import com.itheima.bean.Subarea;
import com.itheima.service.ISubareaService;
import com.itheima.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.util.List;
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
    @Autowired
    private ISubareaService subareaService;

    public String add() {
        subareaService.save(model);
        return LIST;
    }

    public String pageList() {
        String addresskey = model.getAddresskey();
        DetachedCriteria dc = pageBean.getDetachedCriteria();
        if (StringUtils.isNotBlank(addresskey)) {
            dc.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
        }
        Region region = model.getRegion();
        if (region != null) {
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            dc.createAlias("region", "r");
            if (StringUtils.isNotBlank(province)) {
                dc.add(Restrictions.like("r.province", "%" + province + "%"));
            }
            if (StringUtils.isNotBlank(city)) {
                dc.add(Restrictions.like("r.city", "%" + city + "%"));
            }
            if (StringUtils.isNotBlank(district)) {
                dc.add(Restrictions.like("r.district", "%" + district + "%"));
            }
        }
        subareaService.pageList(pageBean);
        toJson(pageBean, "decidedzone","subareas");
        return NONE;
    }

    public String exportXls() throws Exception {
        List<Subarea> list = subareaService.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("分区数据");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("分区编号");
        row.createCell(1).setCellValue("开始编号");
        row.createCell(2).setCellValue("结束编号");
        row.createCell(3).setCellValue("位置信息");
        row.createCell(4).setCellValue("省市区");
        for (Subarea subarea : list) {
            HSSFRow datarow = sheet.createRow(sheet.getLastRowNum() + 1);
            datarow.createCell(0).setCellValue(subarea.getId());
            datarow.createCell(1).setCellValue(subarea.getStartnum());
            datarow.createCell(2).setCellValue(subarea.getEndnum());
            datarow.createCell(3).setCellValue(subarea.getPosition());
            datarow.createCell(4).setCellValue(subarea.getRegion().getName());
        }
        //获得内容类型并设置类型
        String filename = "分区数据.xls";
        String type = ServletActionContext.getServletContext().getMimeType(filename);
        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType(type);
        //获得浏览器类型
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        filename = FileUtils.encodeDownloadFilename(filename, agent);
        ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + filename);
        workbook.write(outputStream);
        return NONE;
    }

    public String listAjax() {
        List<Subarea> list = subareaService.findByDecidedzone();
        if (list != null) {
            for (Subarea subarea : list) {
                subarea.getSubareaId();
            }
            toJson(list, "id", "startnum", "endnum", "single", "decidedzone", "region");
        }
        return NONE;
    }

    public String listByDecidedzoneId(){
        List<Subarea> list = subareaService.findByDecidedzone(decidedzoneId);
        toJson(list,"decidedzone");
        return NONE;
    }

    private String decidedzoneId;

    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }
}
