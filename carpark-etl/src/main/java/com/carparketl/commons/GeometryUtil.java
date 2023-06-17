package com.carparketl.commons;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {
    public static final int SRID_WGS84 = 4326;

    public static Point convertCoordinateToPoint(final Double latitude, final Double longitude) {
        GeometryFactory geometry = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), SRID_WGS84);
        return geometry.createPoint(new Coordinate(longitude, latitude));
    }
}
