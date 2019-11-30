

create table AUD_COLUMNA
(
  nom_tabla VARCHAR2(30),
  nom_colum VARCHAR2(30),
  huella    VARCHAR2(3200),
  fecha     DATE
);



create table AUD_TABLA
(
  nom_tabla VARCHAR2(30),
  huella    VARCHAR2(3200),
  fecha     DATE
);


create table LOG_CAMBIO_DB
(
  tabla                VARCHAR2(30),
  columna              VARCHAR2(30),
  huella_antigua       VARCHAR2(3200),
  fecha_huella_antigua DATE,
  huella_nueva         VARCHAR2(3200),
  fecha_huella_nueva   DATE,
  fecha_aud            DATE
);