module.exports = {
  buildOptions: {
    out: "./target/build",
  },
  exclude: ['**/node_modules/**/*', '**/*.tmp'],
  mount: {
    public: "/",
    "target/scala-3.3.1/ilokano-nga-biblia-fastopt": "/",
  },
}
