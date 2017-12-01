package gxf.dev.biz.admin.mapper;

import gxf.dev.biz.admin.model.Resources;
import gxf.dev.biz.admin.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface ResourcesMapper extends MyMapper<Resources> {

    public List<Resources> queryAll();

    public List<Resources> loadUserResources(Map<String, Object> map);

    public List<Resources> queryResourcesListWithSelected(Integer rid);
}