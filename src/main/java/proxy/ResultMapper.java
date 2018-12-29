package proxy;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Martin Ma
 * @Date 2018/12/29
 **/
public class ResultMapper<T> {

    public List<T> transform(ResultSet rs, Class clazz) throws
            SQLException,
            IllegalAccessException,
            InstantiationException,
            NoSuchFieldException,
            InvocationTargetException {

        List<T> result = null;
        while (rs.next()) {
            ResultSetMetaData metadata = rs.getMetaData();
            T bean = (T) clazz.newInstance();
            for (int i = 0; i < metadata.getColumnCount(); i++) {
                String colName = metadata.getColumnName(i + 1);
                Object value = rs.getObject(i + 1);
                Field field = clazz.getField(colName);
                if (field != null && value != null)
                    BeanUtils.setProperty(bean, field.getName(), value);
            }
            result.add(bean);
        }

        return result;
    }
}
