/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
  //Diccionario de datosS
  attributes: {
    cedula:{//nombre atributo
      type: 'string',
      required: true,//por defecto es falso
      columnName: 'usr_cedula',
      unique: true,
      minLength:10,
      maxLength:25
    },
    nombre:{
      type: 'string',
      minLength:3,
      required: true//por defecto es falso
    },
    correo:{
      type: 'string',
      isEmail:true//por defecto es falso
    },
    estadoCivil:{
      type: 'string',
      isIn:['Soltero', 'Casado', 'Divorciado', 'Viudo','Union Libre'],
      defaultsTo:'Soltero'
    },
    password:{
      type: 'string',
      regex:/^[a-zA-Z]\w{3,14}$/
    },
    pokemons:{//one to many
      collection:'pokemon',//referencia al modelo
      via:'usuario'//nombre foreign key
    }
  },
};

