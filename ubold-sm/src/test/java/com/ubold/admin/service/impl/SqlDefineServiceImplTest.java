package com.ubold.admin.service.impl;

import com.ubold.admin.repository.SqlDefineRepository;
import com.ubold.admin.request.SqlDefineRequest;
import com.ubold.admin.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
* SqlDefineServiceImpl Tester.
*
* @author <Authors name>
* @since <pre>ʮһ�� 8, 2017</pre>
* @version 1.0
*/
public class SqlDefineServiceImplTest {

    @InjectMocks
    private SqlDefineServiceImpl sqlDefineServiceImpl;

    @Mock
    SqlDefineRepository sqlDefineRepository;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void after() throws Exception {

    }

    /**
    *
    * Method: persistent(SqlDefineRequest sqlDefineRequest)
    *
    */
    @Test
    public void testPersistent() throws Exception {
        //Step 0: Prepare test data
        SqlDefineRequest sqlDefineRequest = new SqlDefineRequest();
        //Step 1: Record

        //Step 2: Replay
        Response response = sqlDefineServiceImpl.persistent(sqlDefineRequest);
        //Step 3: Verify
        Assert.assertTrue(response.checkSuccess());
    }

    /**
    *
    * Method: getColumnsBySqlId(String sqlId)
    *
    */
    @Test
    public void testGetColumnsBySqlId() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

    }

    /**
    *
    * Method: deleteByDataViewCode(String code, JSONObject row)
    *
    */
    @Test
    public void testDeleteByDataViewCode() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

    }

    /**
    *
    * Method: modifyByDataViewCode(String code, JSONObject row)
    *
    */
    @Test
    public void testModifyByDataViewCode() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

    }

    /**
    *
    * Method: createByDataViewCode(String code, JSONObject row)
    *
    */
    @Test
    public void testCreateByDataViewCode() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

    }

    /**
    *
    * Method: fetch(SqlDefineFetchParam sqlDefineFetchParam)
    *
    */
    @Test
    public void testFetch() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

    }

    /**
    *
    * Method: getBootstrapTableResponse(BootstrapSearchParam bootstrapSearchParam, String sqlId)
    *
    */
    @Test
    public void testGetBootstrapTableResponseForBootstrapSearchParamSqlId() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

    }

    /**
    *
    * Method: getBootstrapTableResponse(Integer pageSize, Integer pageNumber, String searchText, String sortName, String sortOrder, String sqlId, BootstrapSearchParam bootstrapSearchParam)
    *
    */
    @Test
    public void testGetBootstrapTableResponseForPageSizePageNumberSearchTextSortNameSortOrderSqlIdBootstrapSearchParam() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

    }

    /**
    *
    * Method: ztree(ZtreeParamsRequest ztreeParamsRequest)
    *
    */
    @Test
    public void testZtree() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

    }


    /**
    *
    * Method: uniqueCheck(ColumnParam field, SqlDefine sqlDefine, JSONObject row, boolean insert)
    *
    */
    @Test
    public void testUniqueCheck() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

            /*
            try {
               Method method = SqlDefineServiceImpl.getClass().getMethod("uniqueCheck", ColumnParam.class, SqlDefine.class, JSONObject.class, boolean.class);
               method.setAccessible(true);
               method.invoke(<Object>, <Parameters>);
            } catch(NoSuchMethodException e) {
            } catch(IllegalAccessException e) {
            } catch(InvocationTargetException e) {
            }
            */
        }

    /**
    *
    * Method: setFieldTitle(ColumnParam sqlViewField, String field)
    *
    */
    @Test
    public void testSetFieldTitle() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

            /*
            try {
               Method method = SqlDefineServiceImpl.getClass().getMethod("setFieldTitle", ColumnParam.class, String.class);
               method.setAccessible(true);
               method.invoke(<Object>, <Parameters>);
            } catch(NoSuchMethodException e) {
            } catch(IllegalAccessException e) {
            } catch(InvocationTargetException e) {
            }
            */
        }

    /**
    *
    * Method: getTreeNode(TreeOptionsParam treeOptionsParam)
    *
    */
    @Test
    public void testGetTreeNode() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

            /*
            try {
               Method method = SqlDefineServiceImpl.getClass().getMethod("getTreeNode", TreeOptionsParam.class);
               method.setAccessible(true);
               method.invoke(<Object>, <Parameters>);
            } catch(NoSuchMethodException e) {
            } catch(IllegalAccessException e) {
            } catch(InvocationTargetException e) {
            }
            */
        }

    /**
    *
    * Method: appendIds(List<Map<String,Object>> mapList, String key)
    *
    */
    @Test
    public void testAppendIds() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

            /*
            try {
               Method method = SqlDefineServiceImpl.getClass().getMethod("appendIds", List<Map<String,Object>>.class, String.class);
               method.setAccessible(true);
               method.invoke(<Object>, <Parameters>);
            } catch(NoSuchMethodException e) {
            } catch(IllegalAccessException e) {
            } catch(InvocationTargetException e) {
            }
            */
        }

    /**
    *
    * Method: warpTreeSql(String sql, String field)
    *
    */
    @Test
    public void testWarpTreeSql() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

            /*
            try {
               Method method = SqlDefineServiceImpl.getClass().getMethod("warpTreeSql", String.class, String.class);
               method.setAccessible(true);
               method.invoke(<Object>, <Parameters>);
            } catch(NoSuchMethodException e) {
            } catch(IllegalAccessException e) {
            } catch(InvocationTargetException e) {
            }
            */
        }

    /**
    *
    * Method: findAllNode(String sql, Object pId, TreeOptionsParam treeVo)
    *
    */
    @Test
    public void testFindAllNode() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

            /*
            try {
               Method method = SqlDefineServiceImpl.getClass().getMethod("findAllNode", String.class, Object.class, TreeOptionsParam.class);
               method.setAccessible(true);
               method.invoke(<Object>, <Parameters>);
            } catch(NoSuchMethodException e) {
            } catch(IllegalAccessException e) {
            } catch(InvocationTargetException e) {
            }
            */
        }

    /**
    *
    * Method: isParent(String parent, SqlDefine sqlDefine, ZtreeParamsRequest ztreeParamsRequest)
    *
    */
    @Test
    public void testIsParent() throws Exception {
        //Step 0: Prepare test data

        //Step 1: Record

        //Step 2: Replay

        //Step 3: Verify

            /*
            try {
               Method method = SqlDefineServiceImpl.getClass().getMethod("isParent", String.class, SqlDefine.class, ZtreeParamsRequest.class);
               method.setAccessible(true);
               method.invoke(<Object>, <Parameters>);
            } catch(NoSuchMethodException e) {
            } catch(IllegalAccessException e) {
            } catch(InvocationTargetException e) {
            }
            */
        }

}
