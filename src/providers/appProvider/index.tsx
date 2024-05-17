import React from "react";
import { ProfileDataProvider } from "../../util/Profile";
import { ConsultoresProvider } from "../../context/consultores";
import { ValidationProvider } from "../../util/ValidationContext";

const AppProvider = ({ children }) => {
  return (
    <ProfileDataProvider>
      <ValidationProvider>
      <ConsultoresProvider>
        {children}
      </ConsultoresProvider>
      </ValidationProvider>
    </ProfileDataProvider>
  );
};

export default AppProvider;
