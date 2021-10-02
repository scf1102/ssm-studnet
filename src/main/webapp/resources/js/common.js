//将表单serializeArray()序列化的数组转为json对象
function formDataObj(selector) {
    var arr = $(selector).serializeArray();
    var retObj={};
    $.each(arr,function (i,ele) {
        if (retObj[ele["name"]]){
            retObj[ele["name"]] += "," + ele["value"];
        }else {
            retObj[ele["name"]] = ele["value"];
        }
    });
    return retObj;
}