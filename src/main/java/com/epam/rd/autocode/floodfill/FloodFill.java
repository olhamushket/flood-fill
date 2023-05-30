package com.epam.rd.autocode.floodfill;

public interface FloodFill {
    void flood(final String map, final FloodLogger logger);

    static FloodFill getInstance() {
        return new FloodFill() {
            @Override
            public void flood(String map, FloodLogger logger) {
                if (map.indexOf(LAND) == -1){
                    logger.log(map);
                    return;
                }
                int charsInLine = map.indexOf('\n');
                logger.log(map);
                StringBuilder newMap = new StringBuilder(map);
                for (int i = 0; i < map.length(); i++) {
                    if(map.charAt(i)==WATER){
                        int indexLeft = i-1;
                        int indexRight = i+1;
                        int indexUp = i-(charsInLine+1);
                        int indexDown = i+(charsInLine+1);

                        if (indexLeft>=0 && map.charAt(indexLeft)!='\n')
                            newMap.replace(indexLeft,indexLeft+1,"░");
                        if (indexRight<map.length() && map.charAt(indexRight)!='\n')
                            newMap.replace(indexRight,indexRight+1,"░");
                        if (indexUp>=0 && map.charAt(indexUp)!='\n')
                            newMap.replace(indexUp,indexUp+1,"░");
                        if (indexDown<map.length() && map.charAt(indexDown)!='\n')
                            newMap.replace(indexDown,indexDown+1,"░");
                    }
                }
                flood(newMap.toString(),logger);
            }
        };
    }

    char LAND = '█';
    char WATER = '░';

}
