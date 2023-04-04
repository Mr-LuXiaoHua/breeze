/**
 * 定义资源编码
 * @type {{}}
 */
const resource_code_config = {
    // 资源管理
    res_manage_add: "res_manage_add",
    res_manage_edit: "res_manage_edit",
    res_manage_delete: "res_manage_delete",

    // 机构管理
    org_manage_add: "org_manage_add",
    org_manage_edit: "org_manage_edit",
    org_manage_delete: "org_manage_delete",

    // 角色管理
    role_manage_add: "role_manage_add",
    role_manage_edit: "role_manage_edit",
    role_manage_delete: "role_manage_delete",
    role_manage_assign_resource: "role_manage_assign_resource",


    // 用户管理
    user_manage_add: "user_manage_add",
    user_manage_edit: "user_manage_edit",
    user_manage_delete: "user_manage_delete"
}


const status_code = {
    UNAUTHORIZED: 401,
    FORBIDDEN: 403,
}





/**
 * 分页查询
 * @param pageListUrl 分页查询url
 * @param currentPage 当前页码
 * @param pageSize    每页显示记录数
 */
function pageQuery(pageListUrl, currentPage, pageSize) {
    let data = {
        "currentPage": currentPage,
        "pageSize": pageSize
    };
    $.ajax({
        type: "POST",
        dataType: "json",
        url: pageListUrl,
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data),
        success: function(result) {
            if (result.code==status_code.UNAUTHORIZED) {
                window.location.href = "/static/html/login.html";
            } else {
                applyTable(result);
                initPaper(pageListUrl, result);
            }
        }
    });


}



function initPaper(pageListUrl, result) {
    $('#myPager').pager({
       /* page: result.data.current,
        recTotal:result.data.totalRecord,
        recPerPage: result.data.size,*/
        onPageChange: function(state, oldState) {
            if (state.page !== oldState.page) {
                console.log('页码从', oldState.page, '变更为', state.page);
                pageQuery(pageListUrl, state.page, 10);
            }
        }
    });

    let myPager = $('#myPager').data('zui.pager');
    myPager.set({
        page: result.data.current,
        recTotal:result.data.totalRecord,
        recPerPage: result.data.size
    });

}


function serializeArrayToJson(arr) {
    let obj = {};
    $.each(arr, function (index, item) {
        obj[item.name] = item.value;
    });
    return obj;
}


/**
 * 发送http请求
 * @param url  请求的地址
 * @param data 请求的数据
 * @param resultHandleFun 结果处理函数
 */
function doHttpRequest(url, data, resultHandleFun) {
    console.log("请求url:"+url);
    $.ajax({
        type: "POST",
        dataType: "json",
        url: url,
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data),
        success: function(result) {
            if (result.code==status_code.UNAUTHORIZED) {
                console.log("未登录");
                window.location.href = "/static/html/login.html";
            } else {
                resultHandleFun(result);
            }

        }
    });
}


/**
 * 获取登录信息
 * @returns {any}
 */
function getLoginInfo() {
    let loginInfo = JSON.parse(sessionStorage.getItem("LOGIN_INFO"));
    return loginInfo;
}

/**
 * 是否有权限
 * @param resourceCode 资源编码
 */
function hasPrivilege(resourceCode) {
    let loginInfo = getLoginInfo();
    if($.inArray(resourceCode, loginInfo.resourceCodeList) != -1) {
        return true;
    }
    return false;
}