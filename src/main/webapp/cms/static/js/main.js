// 转换为日期
function strToDate(date) {
	return new Date(date.replace(/-/g, "/"));
}
// 日期加减
function addDate(date, dadd) {
	date = date.valueOf();
	date = date + dadd * 24 * 60 * 60 * 1000;
	return new Date(date);
}
// 取当前月第一天
function getFirstDay(split) {
	var myDate = new Date();
	var year = myDate.getFullYear();
	var month = myDate.getMonth() + 1;
	if (month < 10) {
		month = "0" + month;
	}
	return year + split + month + split + "01";
}
// 取当前年月日
function getCurDate(split) {
	var myDate = new Date();
	var year = myDate.getFullYear();
	var month = myDate.getMonth() + 1;
	if (month < 10) {
		month = "0" + month;
	}
	var day = myDate.getDate();
	if (day < 10) {
		day = "0" + day;
	}
	return year + split + month + split + day;
}
// 取当前年月日的偏移
function getOffsetDate(split, opt_date, num) {
	var myDate = new Date();
	if (opt_date == "year") {
		myDate.setFullYear(myDate.getFullYear() + num);
	}
	if (opt_date == "month") {
		myDate.setMonth(myDate.getMonth() + num);
	}
	if (opt_date == "day") {
		myDate.setDate(myDate.getDate() + num);
	}
	var year = myDate.getFullYear();
	var month = myDate.getMonth() + 1;
	if (month < 10) {
		month = "0" + month;
	}
	var day = myDate.getDate();
	if (day < 10) {
		day = "0" + day;
	}
	return year + split + month + split + day;
}
// 时间比较
function diffDate(oDate, tDate) {
	var arr = oDate.split("-");
	var starttime = new Date(arr[0], arr[1], arr[2]);
	var starttimes = starttime.getTime();

	var arrs = tDate.split("-");
	var lktime = new Date(arrs[0], arrs[1], arrs[2]);
	var lktimes = lktime.getTime();

	if (oDate != null && tDate !== null && starttimes >= lktimes) {
		return false;// oDate大于等于tDate
	} else
		return true;
}
// null值默认
function setDefault(data, defaultData) {
	if (data == null || data.length == 0 || data == '') {
		data = defaultData;
	}
	return data;
}
// --------------------------------------
jQuery(document).ready(function($) {

});