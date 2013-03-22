<!DOCTYPE html>
<html>
  <head>
    <title>Groovy Grails Tag Cloud</title>
    <style>
    body {
      font-family: Arial;
      font-size: 12px;
    }
      
    #main {
      margin: 10px auto;
      width: 640px;
    }

    #postal-count {
      margin: 20px 0;
    }

    #spinner {
      position: relative;
      top: 4px;
      display: none;
    }
    </style>
    <r:require module='jquery' />
    <r:layoutResources/>
    <r:external uri='/js/tagCloud.js' />
  </head>
  <body>
    <div id="main">
      <h1>US States Tag Cloud</h1>
      <h4>
        Tag cloud based on the number of postal codes for each U.S.' state.
      </h4>
      <div id="tag-cloud">
        <g:if test="${tags.size() == 0}">
        No data.
        </g:if>
        <g:else>
        <tc:tagCloud tags="${tags}" size="${[start: 1.0, end: 1.5, unit: 'em']}" />
        </g:else>
      </div>
      <div id="postal-count">
        ${stateInfo}
      </div>
      <div id="actions">
        <g:form>
        <g:submitToRemote url="[controller:'tagCloud', action:'clear']" value="Clear all data" update="tag-cloud" />
        <g:submitToRemote url="[controller:'tagCloud', action:'recollect']" value="Recollect data" onComplete="recollect(false)" onLoading="recollect(true)" update="tag-cloud" />
        <img id="spinner" src="${createLinkTo(dir: 'images', file: 'spinner.gif')}"  />
        </g:form>
      </div>
    </div>
  </body>
    
</html>