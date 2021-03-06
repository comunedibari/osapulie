#! /bin/bash

declare -r SVN=${SVN:-"/usr/bin/svn"}
declare -r SED=${SED:-"/usr/bin/sed"}

# $1 the version number to use
# $2 the directory of the monolithic project or project module
function lock_pom {
    if [ ! -f "$2/pom.xml" ] ; then
        return
    fi

    echo "Setting the version of parent POM in $2/pom.xml"
    $SED "s/TRUNK-SNAPSHOT/$1/1" "$2/pom.xml" > "$2/pom.xml.tmp"
    RETVAL=$?
    if [ $RETVAL != 0 ] ; then
        echo "Setting the version of the parent POM in $2/pom.xml failed with a status code of $RETVAL"
        exit 1;
    fi
    mv "$2/pom.xml.tmp" "$2/pom.xml"
}

# $1 the version number to use
# $2 the directory of the monolithic project or project module
function lock_checkstyle {
    if [ ! -f "$2/.checkstyle" ] ; then
        return
    fi

    echo "Setting the version of the checkstyle configuration located in $2/.checkstyle"
    $SED "s/java-parent-project-v2\/trunk\/resources/java-parent-project-v2\/tags\/$1\/resources/1" "$2/.checkstyle" > "$2/.checkstyle.tmp"
    RETVAL=$?
    if [ $RETVAL != 0 ] ; then
        echo "Setting the version of the checkstyle configuration located in $2/.checkstyle failed with a status code of $RETVAL"
        exit 1;
    fi
    mv "$2/.checkstyle.tmp" "$2/.checkstyle"
}
