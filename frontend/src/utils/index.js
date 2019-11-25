/**
 * Created by jiachenpan on 16/11/18.
 */

export function parseTime(time) {
  if (time) {
    var date = new Date(time)
    var year = date.getFullYear()
    /* 在日期格式中，月份是从0开始的，因此要加0
     * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
     * */
    var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
    var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
    var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
    var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
    var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
    // 拼接
    return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
  } else {
    return ''
  }
}
export function parseDate(dateTime) {
  if (dateTime) {
    var date = new Date(dateTime)
    var year = date.getFullYear()
    var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
    var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
    return year + '-' + month + '-' + day
  } else {
    return ''
  }
}
export function formatTime(time, option) {
  time = +time * 1000
  const d = new Date(time)
  const now = Date.now()

  const diff = (now - d) / 1000

  if (diff < 30) {
    return '刚刚'
  } else if (diff < 3600) {
    // less 1 hour
    return Math.ceil(diff / 60) + '分钟前'
  } else if (diff < 3600 * 24) {
    return Math.ceil(diff / 3600) + '小时前'
  } else if (diff < 3600 * 24 * 2) {
    return '1天前'
  }
  if (option) {
    return parseTime(time, option)
  } else {
    return (
      d.getMonth() +
      1 +
      '月' +
      d.getDate() +
      '日' +
      d.getHours() +
      '时' +
      d.getMinutes() +
      '分'
    )
  }
}

export function debounce(func, wait, immediate) {
  let timeout, args, context, timestamp, result

  const later = function() {
    // 据上一次触发时间间隔
    const last = +new Date() - timestamp

    // 上次被包装函数被调用时间间隔last小于设定时间间隔wait
    if (last < wait && last > 0) {
      timeout = setTimeout(later, wait - last)
    } else {
      timeout = null
      // 如果设定为immediate===true，因为开始边界已经调用过了此处无需调用
      if (!immediate) {
        result = func.apply(context, args)
        if (!timeout) context = args = null
      }
    }
  }

  return function(...args) {
    context = this
    timestamp = +new Date()
    const callNow = immediate && !timeout
    // 如果延时不存在，重新设定延时
    if (!timeout) timeout = setTimeout(later, wait)
    if (callNow) {
      result = func.apply(context, args)
      context = args = null
    }

    return result
  }
}

export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

// 替换邮箱字符
export function regEmail(email) {
  if (String(email).indexOf('@') > 0) {
    const str = email.split('@')
    let _s = ''
    if (str[0].length > 3) {
      for (var i = 0; i < str[0].length - 3; i++) {
        _s += '*'
      }
    }
    var new_email = str[0].substr(0, 3) + _s + '@' + str[1]
  }
  return new_email
}

// 替换手机字符
export function regMobile(mobile) {
  if (mobile.length > 7) {
    var new_mobile = mobile.substr(0, 3) + '****' + mobile.substr(7)
  }
  return new_mobile
}

export function showEventPeriod(val) {
  var keys = ['P1','P2'];
  var values = ['當日','期間']

  for(var i=0; i<keys.length; i++){
    if(val == keys[i]){
      return values[i];
    }
  }
  return '';
}

export function showEventRestriction(val) {
  var keys = ['R1','R2','R3'];
  var values = ['當日限兌一次','活動期間限兌一次','不限']

  for(var i=0; i<keys.length; i++){
    if(val == keys[i]){
      return values[i];
    }
  }
  return '';
}

export function showEventStatus(val) {
  var keys = ['行銷設定','營業設定','審核中','已通過','重設品群']
  var values = ['N','B','W','S','G'];
  
  for(var i=0; i<keys.length; i++){
    if(val == keys[i]){
      return values[i];
    }
  }
  return '';
}

export function showEventConfStatus(val) {
  var keys = ['新建立','營業設定','送審','待審核','重設商品碼','重設品群','通過','退回']
  var values = ['N','B','R','W','P','G','S','F'];

  for(var i=0; i<keys.length; i++){
    if(val == keys[i]){
      return values[i];
    }
  }
  return '';
}

export function showEventDeptId(val) {
  var keys = ['仕女服飾部','紳士嬰童家用部','食品餐飲部']
  var values = ['11','13','15'];

  for(var i=0; i<keys.length; i++){
    if(val == keys[i]){
      return values[i];
    }
  }
  return '';
}

export function showEventStatusCode(val) {
  var keys = ['N','B','W','S','G'];
  var values = ['行銷設定','營業設定','審核中','已通過','重設品群']

  for(var i=0; i<keys.length; i++){
    if(val == keys[i]){
      return values[i];
    }
  }
  return '';
}
export function showEventConfStatusCode(val) {
  var keys = ['N','B','R','W','P','G','S','F'];
  var values = ['新建立','營業設定','送審','待審核','重設商品碼','重設品群','通過','退回']
  
  for(var i=0; i<keys.length; i++){
    if(val == keys[i]){
      return values[i];
    }
  }
  return '';
}

export function showEventType(val) {
  var keys = ['T1','T2','T3','T4'];
  var values = ['銀行禮','來店禮','滿額禮','抽獎']

  for(var i=0; i<keys.length; i++){
    if(val == keys[i]){
      return values[i];
    }
  }
  return '';
}