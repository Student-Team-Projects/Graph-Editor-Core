add_library(tools SHARED "")
target_sources(tools PRIVATE tools.cpp planarity.cpp triangulate.cpp sample_lib.cpp)