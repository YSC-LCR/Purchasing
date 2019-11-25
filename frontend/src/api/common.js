import request from '@/utils/request'

export function queryAll(path) {
  return request({
    url: path + '/queryAll',
    method: 'get'
  })
}

export function del(path, pk) {
  return request({
    url: path + '/delete/' + pk,
    method: 'delete'
  })
}

export function deleteData(path, data) {
  return request({
    url: path + '/delete',
    method: 'POST',
    data
  })
}

export function add(path, data) {
  return request({
    url: path + '/create',
    method: 'POST',
    data
  })
}

export function edit(path, data) {
  return request({
    url: path + '/update',
    method: 'POST',
    data
  })
}
export function getRrestrictionZhtw(key){
  var zhtwMap = new Map();
  zhtwMap.set('R1', '當日限兌一次');
  zhtwMap.set('R2', '活動期間限兌一次');
  zhtwMap.set('R3', '不限');
  return zhtwMap.get(key);
}
export function getEventPeriodZhtw(key){
  //活動期間
  var zhtwMap = new Map();
  zhtwMap.set('P1', '當日');
  zhtwMap.set('P2', '期間');
  return zhtwMap.get(key);
}
export function getEventStatusZhtw(key){
  //活動商品碼狀態
  var zhtwMap = new Map();
  zhtwMap.set('N', '行銷設定');
  zhtwMap.set('B', '營業設定');
  zhtwMap.set('W', '待審核');
  zhtwMap.set('S', '已通過');
  zhtwMap.set('P', '重設商品碼');
  zhtwMap.set('G', '重設品群');
  return zhtwMap.get(key);
}
export function getSettingStatusZhtw(key){
  //是否完成銀行滿額禮設定
  var zhtwMap = new Map();
  zhtwMap.set('N', '未設定');
  zhtwMap.set('Y', '已設定');
  return zhtwMap.get(key);
}

export function getPurchaseTypeZhtw(key){
  //消費類型, C1：單筆, C2：當日累計
  var zhtwMap = new Map();
  zhtwMap.set('C1', '單筆');
  zhtwMap.set('C2', '當日累計');
  return zhtwMap.get(key);
}

export function getSwipeTypeZhtw(key){
  //刷卡類型, W1：一般, W2：分期
  var zhtwMap = new Map();
  zhtwMap.set('W1', '一般');
  zhtwMap.set('W2', '分期');
  return zhtwMap.get(key);
}
	
export function getBonusFlagZhtw(key){
  //是否需要紅利集點, Y/N
  var zhtwMap = new Map();
  zhtwMap.set('Y', '需要');
  zhtwMap.set('N', '不需要');
  return zhtwMap.get(key);
}

export function getTableSort(prop, order){
  var orderBy = prop+",";
  if(order === 'descending'){
    orderBy = orderBy+'desc';
  }else{
    orderBy = orderBy+'asc';
  }
  return orderBy;
}