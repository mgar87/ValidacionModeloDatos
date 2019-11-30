CREATE OR REPLACE PACKAGE BODY PKGV_VAL_DB_MODEL IS

PROCEDURE FNDB_REGISTRA_HUELLA IS

CURSOR cur_modelo IS
 SELECT  table_name  TABLA,
         column_name COLUMNA, 
         estructura  ESTRUCTURA, 
         sha256.encrypt(ESTRUCTURA) HUELLA FROM (
 SELECT table_name, column_name, table_name || '.' || column_name || '.' || concat(concat(concat(data_type,'('),data_length),')') ESTRUCTURA
  FROM user_tab_columns
 ORDER BY table_name ASC, COLUMN_NAME ASC ) LINEA_BASE;
 

CURSOR cur_tablas IS
  SELECT * FROM USER_TABLES;

CURSOR cur_metadata (vca_tabla VARCHAR2) IS
  SELECT sha256.encrypt(to_char(dbms_metadata.get_ddl('TABLE', vca_tabla)))   
  FROM dual;
  
vca_metadata VARCHAR2(3200);

vrg_audcolumna AUD_COLUMNA%ROWTYPE;
vrg_audtabla   AUD_TABLA%ROWTYPE;


BEGIN
  


-- REGISTRO POR COLUMA
FOR rec IN cur_modelo LOOP
  vrg_audcolumna.nom_tabla := rec.tabla;
  vrg_audcolumna.nom_colum := rec.columna;
  vrg_audcolumna.huella    := rec.huella;
 vrg_audcolumna.fecha     := SYSDATE;
  
  PRDB_INSERTAR_AUD_COLUMNA(vrg_audcolumna);
  
END LOOP;


-- REGISTRO METADATA DE LA TABLA
FOR rec IN cur_tablas LOOP
  
  OPEN cur_metadata(rec.table_name);
  FETCH cur_metadata INTO vca_metadata;
  CLOSE cur_metadata;
  
  vrg_audtabla.nom_tabla := rec.table_name;
  vrg_audtabla.huella := vca_metadata;
  vrg_audtabla.fecha := SYSDATE;

  PRDB_INSERTAR_AUD_TABLA(vrg_audtabla);
  
END LOOP;

COMMIT;


END FNDB_REGISTRA_HUELLA;



PROCEDURE PRDB_INSERTAR_AUD_COLUMNA (prg_audcolumna AUD_COLUMNA%ROWTYPE) IS
  
BEGIN
  
INSERT INTO AUD_COLUMNA VALUES prg_audcolumna;

  
END PRDB_INSERTAR_AUD_COLUMNA;


PROCEDURE PRDB_INSERTAR_AUD_TABLA(prg_audtabla AUD_TABLA%ROWTYPE) IS
  
BEGIN
  
INSERT INTO AUD_TABLA VALUES prg_audtabla;

  
END PRDB_INSERTAR_AUD_TABLA;








PROCEDURE PRDB_VALIDAHUELLA IS


CURSOR cur_ultimoregtabla IS    
 SELECT  AUDULTIMO.NOM_TABLA TABLA,
         AUDULTIMO.HUELLA    ULTIMA_HUELLA,
         AUDULTIMO.FECHA     FECHA_ULT_HUELLA,
         AUDNUEVO.HUELLA     NUEVA_HUELLA,
         AUDNUEVO.FECHA      FECHA_NUEVA_HUELLA
    FROM AUD_TABLA AUDULTIMO, 
         AUD_TABLA AUDNUEVO
    WHERE AUDNUEVO.FECHA = (SELECT MAX(AU.FECHA) 
                               FROM AUD_TABLA AU
                              WHERE AUDULTIMO.NOM_TABLA = AU.NOM_TABLA)
      AND AUDULTIMO.NOM_TABLA = AUDNUEVO.NOM_TABLA
      AND AUDULTIMO.FECHA =  (SELECT MAX(AU.FECHA) 
                               FROM AUD_TABLA AU
                              WHERE AUDULTIMO.NOM_TABLA = AU.NOM_TABLA
                                AND AU.FECHA <> AUDNUEVO.FECHA);

    
CURSOR cur_ultimoregcolumna IS
    SELECT AUDULTIMO.NOM_TABLA TABLA,
          AUDULTIMO.NOM_COLUM COLUMNA,
          AUDULTIMO.HUELLA    ULTIMA_HUELLA,
          AUDULTIMO.FECHA     FECHA_ULT_HUELLA,
          AUDNUEVO.HUELLA     NUEVA_HUELLA,
          AUDNUEVO.FECHA      FECHA_NUEVA_HUELLA
    FROM AUD_COLUMNA AUDULTIMO, 
         AUD_COLUMNA AUDNUEVO
    WHERE AUDNUEVO.FECHA = (SELECT MAX(AU.FECHA) AU
                               FROM AUD_COLUMNA AU
                              WHERE AUDULTIMO.NOM_TABLA = AU.NOM_TABLA
                                AND AUDULTIMO.NOM_COLUM = AU.NOM_COLUM)
      AND AUDULTIMO.NOM_TABLA = AUDNUEVO.NOM_TABLA
      AND AUDULTIMO.NOM_COLUM = AUDNUEVO.NOM_COLUM
      AND AUDULTIMO.FECHA =  (SELECT MAX(AU.FECHA) 
                               FROM AUD_COLUMNA AU
                              WHERE AUDULTIMO.NOM_TABLA = AU.NOM_TABLA
                                AND AUDULTIMO.NOM_COLUM = AU.NOM_COLUM
                                AND AU.FECHA <> AUDNUEVO.FECHA);


    
    

vrg_log_tabla    LOG_CAMBIO_DB%ROWTYPE;
vrg_log_columna  LOG_CAMBIO_DB%ROWTYPE;

BEGIN
  
FOR rec IN cur_ultimoregtabla LOOP
  
IF(rec.ultima_huella <> rec.nueva_huella) THEN
   vrg_log_tabla.tabla          := rec.tabla;
   vrg_log_tabla.huella_antigua := rec.ultima_huella;
   vrg_log_tabla.fecha_huella_antigua := rec.fecha_ult_huella;
   vrg_log_tabla.huella_nueva         := rec.nueva_huella;
   vrg_log_tabla.fecha_huella_nueva   := rec.fecha_nueva_huella;
   vrg_log_tabla.fecha_aud := SYSDATE;

   PRDB_INSERTAR_LOG(vrg_log_tabla);

END IF;

END LOOP;

  COMMIT;



FOR rec IN cur_ultimoregcolumna LOOP
  
IF(rec.ultima_huella <> rec.nueva_huella) THEN
   vrg_log_columna.tabla   := rec.tabla;
   vrg_log_columna.columna := rec.columna;
   vrg_log_columna.huella_antigua       := rec.ultima_huella;
   vrg_log_columna.fecha_huella_antigua := rec.fecha_ult_huella;
   vrg_log_columna.huella_nueva         := rec.nueva_huella;
   vrg_log_columna.fecha_huella_nueva   := rec.fecha_nueva_huella;
   vrg_log_columna.fecha_aud := SYSDATE;

   PRDB_INSERTAR_LOG(vrg_log_columna);

END IF;

END LOOP;

COMMIT;


  
END PRDB_VALIDAHUELLA;




PROCEDURE PRDB_INSERTAR_LOG(prg_log LOG_CAMBIO_DB%ROWTYPE) IS
  
BEGIN
  
INSERT INTO LOG_CAMBIO_DB VALUES prg_log;

  
END PRDB_INSERTAR_LOG;


END PKGV_VAL_DB_MODEL;
