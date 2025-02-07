describe('template spec', () => {
  it('passes', () => {
    cy.intercept('GET','http://localhost:8080/Stugor').as("Stugor")
    cy.visit('http://localhost:5173/')
    cy.wait("@Stugor")
  })
})