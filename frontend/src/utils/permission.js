import store from '@/store'

/**
 * @param {Array} value
 * @returns {Boolean}
 * @example see @/views/auth/directive.vue
 */
export default function checkPermission(value) {
  if (value && value instanceof Array && value.length > 0) {
    const roles = store.getters && store.getters.roles
    const permissionRoles = value
    console.log('value', value);
    console.log('roles', roles);
    
    const hasPermission = roles.some(role => {
      
      return permissionRoles.includes(role)
    })

    if (!hasPermission) {
      return false
    }
    return true
  } else {
    console.error(`need roles! Like v-permission="['admin','editor']"`)
    return false
  }
}

export function getPermission (val) {
  const roles = store.getters && store.getters.roles
  const permissionRoles = val
  const hasPermission = roles.some(role => {
    return permissionRoles.includes(role);
  })
  return hasPermission;
}

export function getDeptByRole (val) {
  var result = '';
  const roles = store.getters && store.getters.roles

  var roleAsName = '';

  for (var i = 0; i < roles.length; i++) {
    if (roles[i].indexOf(val) >= 0) {
      roleAsName = roles[i];
    }
  }

  if(roleAsName.indexOf("ONE") >= 0){
    result = '11';
  } else if (roleAsName.indexOf("TWO") >= 0) {
    result = '13';
  } else if (roleAsName.indexOf("THREE") >= 0){
    result = '15';
  }
  return result;
}
