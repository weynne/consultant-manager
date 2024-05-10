import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <header className="header">
      <a href="./">
        <img height="54px" src="/img/logo-brencorp.png" alt="Logo" />
      </a>
      <nav className="headerNav">
        <ul className="headerUl">
          <li>
            <Link to={'/'}>
              <img src="/img/FolderFilled.svg" />
              Consultores
            </Link>
          </li>
          <li>
            <a href="">
              <img src="/img/LogoutFilled.svg" alt="" />
              Sair
            </a>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
