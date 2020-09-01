/**
 * Carta.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre:{
      type: 'string',
      required: true,
      columnName: 'card_name',
      unique: true
    },
    id:{
      type: 'string',
      required: true,
      columnName: 'card_id',
      unique: true
    },
    nivel:{
      type: 'number',
      columnName: 'card_level',
      isInteger:true,
      isIn:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
    },
    precio:{
      type:'number',
      columnName: 'amount'
    },
    tcg:{
      type:'boolean',
      columnName: 'tcg',
      required:true
    }
  },

};

