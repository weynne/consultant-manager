import {
  Button,
  CardHeader,
  Divider,
  Menu,
  Select,
  MenuItem,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableFooter,
  TableHead,
  TablePagination,
  TableRow,
  TextField,
  Toolbar,
  Container,
} from '@mui/material';

import { Link, NavLink } from 'react-router-dom';

import ProfileContext from '../util/Profile';

import React, { useEffect } from 'react';
import { useConsultores } from '../context/consultores';

const Content = () => {
  const { data, getConsultores, getConsultorBuscar, getConsultor } =
    useConsultores();

  //Table button menu
  const [buttonMenu, setButtonMenu] = React.useState(null);
  const open = Boolean(buttonMenu);

  const [selectedRow, setSelectedRow] = React.useState(null);
  const [selectedId, setSelectedId] = React.useState(null);

  const { setProfileData } = React.useContext(ProfileContext);

  const onVisualizar = () => {
    setProfileData(selectedRow);
    handleClose();
  };

  const handleClose = () => {
    setButtonMenu(null);
  };

  //Table Pagination
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const emptyRows =
    rowsPerPage - Math.min(rowsPerPage, data.length - page * rowsPerPage);

  //Dados backend
  useEffect(() => {
    getConsultores();
  }, []);

  //Buscar consultores
  const [buscaValue, setBuscaValue] = React.useState('');
  const [buscaKey, setBuscaKey] = React.useState('nome');

  const buscarConsultor = () => {
    getConsultorBuscar({ [buscaKey]: buscaValue });
  };

  const handleBtnClick = () => {
    setBuscaValue('');
    getConsultores();
  };

  return (
    <Container>
      <TableContainer className="tableContainer" sx={{ boxShadow: 2 }}>
        <CardHeader
          title="Consultores"
          disableTypography={true}
          sx={{ fontFamily: 'Montserrat', fontWeight: 'bold', fontSize: 20 }}
        />
        <Divider />
        <Toolbar className="toolbar">
          <div className="search">
            <TextField
              id="outlined-input"
              label="Pesquisar"
              size="small"
              aria-label="Pesquisar"
              value={buscaValue}
              onChange={(event) => setBuscaValue(event.target.value)}
            />
            <Select
              id="filtro"
              name="filtro"
              size="small"
              value={buscaKey}
              onChange={(event) => {
                setBuscaKey(event.target.value);
              }}
            >
              <MenuItem value="nome">Nome</MenuItem>
              <MenuItem value="cidade">Cidade</MenuItem>
              <MenuItem value="estado">Estado</MenuItem>
              <MenuItem value="formacao">Formação</MenuItem>
              <MenuItem value="anoDeFormacao">Ano de Formação</MenuItem>
              <MenuItem value="idade">Idade</MenuItem>
            </Select>
            <Button
              id="searchButton"
              aria-label="Pesquisar"
              size="medium"
              variant="contained"
              onClick={buscarConsultor}
            >
              <img src="/img/searchIcon.svg" alt="" />
            </Button>
            <Button
              size="small"
              variant="outlined"
              sx={{
                color: '#1CB5D5',
                borderColor: '#1CB5D5',
                display: buscaValue !== '' ? 'block' : 'none',
              }}
              onClick={handleBtnClick}
            >
              Limpar Busca
            </Button>
          </div>
          <Link to={`cadastrar`}>
            <Button
              size="medium"
              variant="contained"
              sx={{ boxShadow: 2, bgcolor: '#1CB5D5' }}
              startIcon={<img src="/img/plusIcon.svg" />}
            >
              Novo Consultor
            </Button>
          </Link>
        </Toolbar>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell sx={{ fontFamily: 'Montserrat', fontWeight: 'bold' }}>
                Nome
              </TableCell>
              <TableCell sx={{ fontFamily: 'Montserrat', fontWeight: 'bold' }}>
                Formação
              </TableCell>
              <TableCell sx={{ fontFamily: 'Montserrat', fontWeight: 'bold' }}>
                Atuação
              </TableCell>
              <TableCell sx={{ fontFamily: 'Montserrat', fontWeight: 'bold' }}>
                Localização
              </TableCell>
              <TableCell sx={{ fontFamily: 'Montserrat', fontWeight: 'bold' }}>
                CAT
              </TableCell>
              <TableCell></TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {data
              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              .map((row) => (
                <TableRow key={row.id} hover={true}>
                  <TableCell component="th" scope="row">
                    {row.nome}
                  </TableCell>
                  <TableCell>
                    {
                      row.formacoes.toSorted(
                        (a, b) => b.anoConclusao - a.anoConclusao,
                      )[0].nome
                    }
                  </TableCell>
                  <TableCell>
                    {row.profissoes.map((f) => f.area)[0].toString()}
                  </TableCell>
                  <TableCell>{`${row.cidade.nome}-${row.cidade.estado.uf}`}</TableCell>
                  <TableCell>{row.cat != [] ? 'Sim' : 'Não'}</TableCell>
                  <TableCell>
                    <Button
                      id="menuButton"
                      variant="contained"
                      aria-controls={open ? 'basic-menu' : undefined}
                      aria-haspopup="true"
                      aria-expanded={open ? 'true' : undefined}
                      onClick={(event) => {
                        setButtonMenu(event.currentTarget);
                        setSelectedId(row.id);
                        setSelectedRow(row);
                      }}
                    >
                      <img src="/img/arrowIcon.svg" alt="" />
                    </Button>
                    <Menu
                      elevation={1}
                      id="basic-menu"
                      anchorEl={buttonMenu}
                      open={open}
                      onClose={handleClose}
                      MenuListProps={{
                        'aria-labelledby': 'menuButton',
                      }}
                    >
                      <Link to={`visualizar/${selectedId}`}>
                        <MenuItem onClick={onVisualizar}>Visualizar</MenuItem>
                      </Link>
                      <Link to={`editar/${selectedId}`}>
                        <MenuItem onClick={handleClose}>Editar</MenuItem>
                      </Link>
                      <MenuItem onClick={handleClose}>Excluir</MenuItem>
                    </Menu>
                  </TableCell>
                </TableRow>
              ))}
            {emptyRows > 0 && (
              <TableRow style={{ height: 53 * emptyRows }}>
                <TableCell colSpan={6} />
              </TableRow>
            )}
          </TableBody>
          <TableFooter>
            <TableRow>
              <TablePagination
                count={data.length}
                page={page}
                onPageChange={handleChangePage}
                rowsPerPage={rowsPerPage}
                onRowsPerPageChange={handleChangeRowsPerPage}
                rowsPerPageOptions={[10, 20, 40]}
                labelRowsPerPage="Linhas por página"
                labelDisplayedRows={function defaultLabelDisplayedRows({
                  from,
                  to,
                  count,
                }) {
                  return `${from}–${to} de ${
                    count !== -1 ? count : `more than ${to}`
                  }`;
                }}
              />
            </TableRow>
          </TableFooter>
        </Table>
      </TableContainer>
    </Container>
  );
};

export default Content;
