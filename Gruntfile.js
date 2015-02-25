// Generated on 2014-07-03 using generator-jhipster 0.17.2
'use strict';

// # Globbing
// for performance reasons we're only matching one level down:
// 'test/spec/{,*/}*.js'
// use this if you want to recursively match all subfolders:
// 'test/spec/**/*.js'

var proxySnippet = require('grunt-connect-proxy/lib/utils').proxyRequest;

module.exports = function (grunt) {

  // Load grunt tasks automatically
  require('load-grunt-tasks')(grunt);

  // Time how long tasks take. Can help when optimizing build times
  require('time-grunt')(grunt);

  // Configurable paths for the application
  var appConfig = {
    app: require('./bower.json').appPath || 'src/main/webapp/',
    dist: 'src/main/webapp/dist'
  };

  grunt.initConfig({
    yeoman: appConfig,
    watch: {
      bower: {
        files: ['bower.json'],
        tasks: ['wiredep']
      },
      less: {
        files: ['src/main/webapp/less/*.less', 'Gruntfile.js'],
        tasks: ['less']
      },
      styles: {
        files: ['src/main/webapp/css/{,*/}*.css'],
        tasks: ['copy:styles', 'autoprefixer']
      },
      livereload: {
        options: {
          livereload: 35729
      },
      files: [
          'src/main/webapp/{,*/}*.html',
          '.tmp/css/{,*/}*.css',
          '{.tmp/,}src/main/webapp/app/**/{,*/}*.js',
          'src/main/webapp/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}'
        ]
      }
    },

    // The actual grunt server settings
    connect: {
      options: {
        port: 9000,
        // Change this to 'localhost' to deny access to the server from outside.
        hostname: 'localhost',
        livereload: 35729
      },
      livereload: {
        options: {
          open: true,
          base: [
            '.tmp',
            'src/main/webapp'
          ],
          middleware: function (connect) {
            return [
              proxySnippet,
              connect.static(require('path').resolve('src/main/webapp'))
            ];
          }
        }
      },
      test: {
        options: {
         port: 9001,
          base: [
            '.tmp',
            'test',
            'src/main/webapp'
          ]
        }
      },
      dist: {
        options: {
          base: '<%= yeoman.dist %>'
        }
      }
    },
    clean: {
      dist: {
        files: [{
          dot: true,
          src: [
            '.tmp',
            '<%= yeoman.dist %>/*',
            '!<%= yeoman.dist %>/.git*'
          ]
        }]
      },
      server: '.tmp'
    },

    // Add vendor prefixed styles
    autoprefixer: {
      options: {
        browsers: ['last 1 version']
      },
      dist: {
        files: [{
          expand: true,
          cwd: '.tmp/css/',
          src: '{,*/}*.css',
          dest: '.tmp/css/'
        }]
      }
    },

    // Automatically inject Bower components into the app
    wiredep: {
      app: {
        src: ['src/main/webapp/index.html'],
        ignorePath: new RegExp('^src/main/webapp/|../')
      }
    },

    jshint: {
      options: {
        jshintrc: '.jshintrc'
      },
      all: [
        'Gruntfile.js',
        'src/main/webapp/app/**/{,*/}*.js'
      ]
    },
    coffee: {
      options: {
        sourceMap: true,
        sourceRoot: ''
      },
      dist: {
        files: [{
          expand: true,
          cwd: 'src/main/webapp/app',
          src: '{,*/}*.coffee',
          dest: '.tmp/js',
          ext: '.js'
        }]
      },
      test: {
        files: [{
          expand: true,
          cwd: 'test/spec',
          src: '{,*/}*.coffee',
          dest: '.tmp/spec',
          ext: '.js'
        }]
      }
    },
    // not used since Uglify task does concat,
    // but still available if needed
    /*concat: {
      dist: {}
    },*/
    rev: {
      dist: {
        files: {
          src: [
            '<%= yeoman.dist %>/js/**/{,*/}*.js',
            '<%= yeoman.dist %>/css/{,*/}*.css',
            '<%= yeoman.dist %>/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}',
            '<%= yeoman.dist %>/fonts/*'
          ]
        }
      }
    },
    useminPrepare: {
      html: 'src/main/webapp/**/{,*/}*.html',
      options: {
        dest: '<%= yeoman.dist %>'
      }
    },
    usemin: {
      html: ['<%= yeoman.dist %>/**/{,*/}*.html'],
      css: ['<%= yeoman.dist %>/css/{,*/}*.css'],
      options: {
                // basedir: '<%= yeoman.dist %>',
                // dirs: ['<%= yeoman.dist %>/**/*']
                assetsDirs: ['<%= yeoman.dist %>']
      }
    },

    imagemin: {
      dist: {
        files: [{
          expand: true,
          cwd: 'src/main/webapp/images',
          src: '{,*/}*.{png,jpg,jpeg}', // we don't optimize PNG files as it doesn't work on Linux. If you are not on Linux, feel free to use '{,*/}*.{png,jpg,jpeg}'
          dest: '<%= yeoman.dist %>/images'
        }]
      }
    },
    svgmin: {
      dist: {
        files: [{
          expand: true,
          cwd: 'src/main/webapp/images',
          src: '{,*/}*.svg',
          dest: '<%= yeoman.dist %>/images'
        }]
      }
    },
    cssmin: {
      // By default, your `index.html` <!-- Usemin Block --> will take care of
      // minification. This option is pre-configured if you do not wish to use
      // Usemin blocks.
      dist: {
        files: [{
          expand: true,
          cwd: '.tmp/concat/css',
          src: '{,*/}*.css',
          dest: '<%= yeoman.dist %>/css'
        }]
      }
    },
    // htmlmin: {
    //   dist: {
    //     options: {
    //       removeCommentsFromCDATA: false,
    //       // https://github.com/yeoman/grunt-usemin/issues/44
    //       collapseWhitespace: true,
    //       collapseBooleanAttributes: false,
    //       removeAttributeQuotes: false,
    //       removeRedundantAttributes: false,
    //       useShortDoctype: false,
    //       removeEmptyAttributes: false,
    //       removeOptionalTags: false
    //     },
    //     files: [{
    //       expand: true,
    //       cwd: 'src/main/webapp',
    //       src: ['*.html', 'views/*.html'],
    //       dest: '<%= yeoman.dist %>'
    //     }]
    //   }
    // },
    htmlmin: {
      compressViews: {
        options: {
          removeCommentsFromCDATA: true,
          // https://github.com/yeoman/grunt-usemin/issues/44
          collapseWhitespace: true,
          collapseBooleanAttributes: true,
          removeAttributeQuotes: true,
          removeComments: true,
          removeRedundantAttributes: true,
          useShortDoctype: true,
          removeEmptyAttributes: true
        },
        files: [{
          expand: true,
          cwd: '<%= yeoman.dist %>/views',
          src: '{,*/}*.html',
          dest: '<%= yeoman.dist %>/views'
        }]
      },
      compressPartials: {
        options: {
          removeCommentsFromCDATA: true,
          // https://github.com/yeoman/grunt-usemin/issues/44
          collapseWhitespace: true,
          collapseBooleanAttributes: true,
          removeAttributeQuotes: true,
          removeComments: true,
          removeRedundantAttributes: true,
          useShortDoctype: true,
          removeEmptyAttributes: true
        },
        files: [{
          expand: true,
          cwd: '<%= yeoman.dist %>/partial',
          src: '{,*/}*.html',
          dest: '<%= yeoman.dist %>/partial'
        }]
      }
    },
    // Put files not handled in other tasks here
    copy: {
      dist: {
        files: [{
          expand: true,
          dot: true,
          cwd: 'src/main/webapp',
          dest: '<%= yeoman.dist %>',
          src: [
            '*.{ico,png,txt}',
            '.htaccess',
            'images/{,*/}*.{gif,webp}',
            'fonts/*'
          ]
        }, {
          expand: true,
          cwd: '.tmp/images',
          dest: '<%= yeoman.dist %>/images',
          src: [
            'generated/*'
          ]
        }]
      },
      html: {
        expand: true,
        cwd: 'src/main/webapp/',
        dest: '<%= yeoman.dist %>',
        src: '**/{,*/}*.html'
      },
      styles: {
        expand: true,
        cwd: 'src/main/webapp/css',
        dest: '.tmp/css/',
        src: '{,*/}*.css'
      },
      scripts: {
        expand: true,
        cwd: 'src/main/webapp/app',
        dest: '.tmp/app/',
        src: '**/{,*/}*.js'
      },
      less: {
        expand: true,
        cwd: 'src/main/webapp/css',
        dest: '<%= yeoman.dist %>/css',
        src: '{,*/}*.*'
      },
      fonts: {
        expand: true,
        cwd: 'src/main/webapp/bower_components/fontawesome/fonts',
        dest: '<%= yeoman.dist %>/fonts',
        src: '{,*/}*.*'
      }
    },
    concurrent: {
      server: [
        'copy:styles'
      ],
      test: [
        'copy:styles'
      ],
      dist: [
        'copy:styles',
        'copy:scripts',
        'imagemin',
        'svgmin'
        //'htmlmin'
      ]
    },
    karma: {
      unit: {
        configFile: 'src/test/javascript/karma.conf.js',
        singleRun: true
      }
    },
    cdnify: {
      dist: {
        html: ['<%= yeoman.dist %>/*.html']
      }
    },
    ngmin: {
      dist: {
        files: [{
          expand: true,
          cwd: '.tmp/concat/js',
          src: '*.js',
          dest: '.tmp/concat/js'
        }]
      }
    },
    replace: {
      dist: {
        src: ['<%= yeoman.dist %>/index.html'],
          overwrite: true,                 // overwrite matched source files
          replacements: [{
            from: '<div class="development"></div>',
            to: ''
          }]
        }
      },
    uglify: {
      dist: {
        files: [{
            expand: true,
            cwd: '.tmp/concat/js',
            src: '**/*.js',
            dest: '<%= yeoman.dist %>/js'
        }]
      }
    },

    // Turns LESS files into CSS files
    less: {
        dev: {
            options: {
                paths: ["src/main/webapp/**/*.less"],
                compress: true,
                rootpath: "src/main/webapp/",
                relativeUrls: true,
                // strictImports:true,
                // strictMath:true,
                // strictUnits:true,
                sourceMap: true,
                // outputSourceFiles: true,
                // sourceMapFilename: 'app/main.css.map',
                sourceMapFilename: 'src/main/webapp/css/main.css.map',
                // sourceMapURL: "http://localhost:9000/styles/compiled.css.map",
                sourceMapRootpath: '../',
                sourceMapBasepath: 'src/main/webapp/',
            },
            files: {
                "src/main/webapp/css/main.css": "src/main/webapp/less/styles.less",
                // "dist/styles/compiled.css": "app/less/styles.less"
            }
        },
    }
  });

  grunt.registerTask('serve', 'Compile then start a connect web server', function (target) {
    if (target === 'dist') {
      return grunt.task.run(['build', 'connect:dist:keepalive']);
    }

    grunt.task.run([
      'clean:server',
      //'wiredep',
      'concurrent:server',
      'less',
      'autoprefixer',
      'connect:livereload',
      'watch'
    ]);
  });

  grunt.registerTask('server', 'DEPRECATED TASK. Use the "serve" task instead', function (target) {
    grunt.log.warn('The `server` task has been deprecated. Use `grunt serve` to start a server.');
    grunt.task.run(['serve:' + target]);
  });

  grunt.registerTask('test', [
    'clean:server',
    'concurrent:test',
    'autoprefixer',
    'connect:test'
    //'karma'
  ]);

  grunt.registerTask('build', [
    'clean:dist',
    //'wiredep',
    'useminPrepare',
    'concurrent:dist',
    'autoprefixer',
    'concat',
    'copy:dist',
    'ngmin',
    'cssmin:dist',
    'less:dev',
    'copy:less',
    'replace',
    'uglify:dist',
    'rev',
    'copy:html',
    'usemin',
    'htmlmin:compressViews',
    'htmlmin:compressPartials',
    'copy:fonts'
  ]);

  grunt.registerTask('default', [
    'test',
    'build'
  ]);
};
