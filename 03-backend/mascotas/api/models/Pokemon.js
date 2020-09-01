/**
 * Pokemon.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */
module.exports = {

  attributes: {
    nombre:{
      type: 'string'
    },
    usuario: {
      model:'usuario',
      required:true//es opcional 1 muchos 0 muchos
    },
    batalla:{
      model:'batalla'
    }
  },
};