import $ from 'jquery'
import { getToken } from '@/utils/auth'
const baseUrl = process.env.BASE_API
export function findAllBankSetting(){
  var options = [];
  $.ajax({
    url:  baseUrl + '/card/bankCard/findAllBankSetting',
    type: 'GET', //GET
    async: false, //或false,是否非同步
    headers: { "Authorization": 'Bearer ' + getToken() },
    dataType : 'json', // 預期從server接收的資料型態
    success : function(data) {
      $.each(data, function(i, item) {
        var option = {
          value: item.bankId,
          label: item.bankId + ' ' + item.bankName
        }
        
        options.push(option)
      })
    }
  })
  return options
}
export function findAllCardbinBank(){
  var options = [];
  $.ajax({
    url:  baseUrl + '/card/bankCard/findAllCardbinBank',
    type: 'GET', //GET
    async: false, //或false,是否非同步
    headers: { "Authorization": 'Bearer ' + getToken() },
    dataType : 'json', // 預期從server接收的資料型態
    success : function(data) {
      $.each(data, function(i, item) {
        var option = {
          value: item.bankId,
          label: item.bankName
        }
        options.push(option)
      })
    }
  })
  return options
}
export function findAllBank(){
  var options = [];
  $.ajax({
    url:  baseUrl + '/card/bankCard/findAllBankSetting',
    type: 'GET', //GET
    async: false, //或false,是否非同步
    headers: { "Authorization": 'Bearer ' + getToken() },
    dataType : 'json', // 預期從server接收的資料型態
    success : function(data) {
      $.each(data, function(i, item) {
        var option = {
          value: item.bankId,
          label: item.bankName
        }
        
        options.push(option)
      })
    }
  })
  return options
}
export function findAllCardTypeSetting(){
  var options = [];
  $.ajax({
    url:  baseUrl + '/card/bankCard/findAllCardTypeSetting',
    type: 'GET', //GET
    async: false, //或false,是否非同步
    headers: { "Authorization": 'Bearer ' + getToken() },
    dataType : 'json', // 預期從server接收的資料型態
    success : function(data) {
      $.each(data, function(i, item) {
        var option = {
          value: item.id,
          label: item.name
        }
        options.push(option)
      })
    }
  })
  return options
}

export function getCardDetailByBankIdAndCategory(bankId, category) {
  var cards = []
  $.ajax({
    url: baseUrl + '/card/bankCard/getCardDetailByBankIdAndCategory',
    type: 'POST', //GET
    async: false, //或false,是否非同步
    headers: { "Authorization": 'Bearer ' + getToken() },
    cache: false,
    data: { 
      'bankId': bankId,
      'category': category
    },
    dataType : 'json', // 預期從server接收的資料型態
    success : function(data) {
      $.each(data, function(i, item) {
        cards.push({
          "bankId": item.bankId,
          "cardType": item.cardType,
          "cardTypeName": item.cardTypeName,
          "orgType": item.orgType,
          "orgTypeName": item.orgTypeName,
          "cardBin": item.cardBin,
          "category": item.category
        })
      })
    }
  })
  return cards
}

export function initCardTypeSettingMap(){
  var settingMap = new Map();
  var options = findAllCardTypeSetting();
  //console.log("options:", options)
  for(var i = 0; i < options.length; i++){
    settingMap.set(options[i].value, options[i].label);
  }
  //console.log("settingMap:", settingMap)
  return settingMap
}

export function getCardSettingByType(type) {
  var settingMap = new Map();
  $.ajax({
    url: baseUrl + '/card/bankCard/findByTypeOrderById',
    type: 'POST', //GET
    async: false, //或false,是否非同步
    headers: { "Authorization": 'Bearer ' + getToken() },
    cache: false,
    data: { 
      'type': type
    },
    dataType : 'json', // 預期從server接收的資料型態
    success : function(data) {
      $.each(data, function(i, item) {
        settingMap.set(item.id, item.name);
      })
    }
  })
  return settingMap
}