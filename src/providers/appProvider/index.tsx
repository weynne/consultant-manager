import React from "react";
import { ProfileDataProvider } from "../../util/Profile";
import { ConsultoresProvider } from "../../context/consultores";

const AppProvider = ({ children }) => {
  return (
    <ProfileDataProvider>
      <ConsultoresProvider>
        {children}
      </ConsultoresProvider>
    </ProfileDataProvider>
  );
};

export default AppProvider;
