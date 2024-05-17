import React from 'react';

const ValidationContext = React.createContext();

export const ValidationProvider = ({ children }) => {
  const [errors, setErrors] = React.useState({});

  const validateCPF = (cpf) => {
    if (cpf === '') return true;
    const regex = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/;
    return regex.test(cpf);
  };

  const validateCNPJ = (cnpj) => {
    if (cnpj === '') return true;
    const regex = /^\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}$/;
    return regex.test(cnpj);
  };

  const validateTelefone = (telefone) => {
    if (telefone === '') return true;
    const regex = /^\(?\d{2}\)?[\s-]?[\s9]?\d{4}-?\d{4}$/;
    return regex.test(telefone);
  };

  const validateEmail = (email) => {
    if (email === '') return true;
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
  };

  const handleBlur = (name, value) => {
    let error = '';

    if (name === 'cpf' && !validateCPF(value)) {
      error = 'CPF inválido. Formato esperado: 000.000.000-00';
    } else if (name === 'cnpj' && !validateCNPJ(value)) {
      error = 'CNPJ inválido. Formato esperado: 00.000.000/0000-00';
    } else if (name === 'telefone' && !validateTelefone(value)) {
      error = 'Telefone inválido. Formato esperado: (00)00000-0000';
    } else if (name === 'email' && !validateEmail(value)) {
      error = 'Email inválido';
    }

    setErrors((prevErrors) => ({ ...prevErrors, [name]: error }));
  };

  const handleInputChange = (name, value) => {
    setErrors((prevErrors) => ({ ...prevErrors, [name]: '' }));
  };

  const handleSubmit = (formData) => {
    const validationErrors = {};

    if (!validateCPF(formData.cpf)) {
      validationErrors.cpf = 'CPF inválido. Formato esperado: XXX.XXX.XXX-XX';
    }
    if (!validateCNPJ(formData.cnpj)) {
      validationErrors.cnpj =
        'CNPJ inválido. Formato esperado: XX.XXX.XXX/XXXX-XX';
    }
    if (!validateTelefone(formData.telefone)) {
      validationErrors.telefone =
        'Telefone inválido. Formato esperado: (XX)XXXXX-XXXX';
    }
    if (!validateEmail(formData.email)) {
      validationErrors.email = 'Email inválido';
    }

    setErrors(validationErrors);

    return Object.keys(validationErrors).length === 0;
  };

  return (
    <ValidationContext.Provider
      value={{ errors, handleBlur, handleInputChange, handleSubmit }}
    >
      {children}
    </ValidationContext.Provider>
  );
};

export const useValidation = () => React.useContext(ValidationContext);
