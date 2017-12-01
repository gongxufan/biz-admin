package gxf.dev.biz.admin.service;

import com.github.pagehelper.PageInfo;
import gxf.dev.biz.admin.model.Resources;

import java.util.List;
import java.util.Map;

/**
 * Created by gongxufan 2017/4/25.
 */
public interface ResourcesService extends IService<Resources> {
    PageInfo<Resources> selectByPage(Resources resources, int start, int length);

    public List<Resources> queryAll();

    public List<Resources> loadUserResources(Map<String, Object> map);

    public List<Resources> queryResourcesListWithSelected(Integer rid);
}
