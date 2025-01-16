export class Validator {
  static isValidNumber(value) {
      
      if (value == null) return false;
      return /^\d+(\.\d+)?$/.test(value.trim());
  }

  static isValidString(value) {
      
      if (value == null) return false;
      return value.trim().length > 0; 
  }

  static isValidInt(value) {
      
      if (value == null) return false;
      return /^[1-9]\d*$/.test(value.trim()); 
  }

  static isValidLong(value) {
      
      if (value == null) return false;
      return /^[-]?\d{1,19}$/.test(value.trim()); 
  }

  static isValidBinary(value) {
      
      if (value == null) return false;
      return /^[01]+$/.test(value.trim()); 
  }

 
  static validate(value, type) {
      
      if (value == null) return false;

      switch(type) {
          case 'number':
              return Validator.isValidNumber(value);
          case 'int':
              return Validator.isValidInt(value);
          case 'long':
              return Validator.isValidLong(value);
          case 'string':
              return Validator.isValidString(value);
          case 'binary':
              return Validator.isValidBinary(value);
          default:
              return false;
      }
  }
}
